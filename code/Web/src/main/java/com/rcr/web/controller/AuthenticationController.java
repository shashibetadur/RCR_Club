package com.rcr.web.controller;

import com.rcr.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.lang.StringUtils.isEmpty;

@Controller
public class AuthenticationController {

    private AuthenticationService authenticationService;

    //requited for proxy creation
    protected AuthenticationController() {
    }

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping("/login/prompt")
    public ModelAndView loginForm(@RequestParam(value = "redirectUrl", defaultValue = "") String redirectUrl, @RequestParam(value = "errorMessage", defaultValue = "") String errorMessage) {
        ModelAndView modelAndView = new ModelAndView("loginForm", "redirectUrl", redirectUrl);
        modelAndView.getModelMap().put("errorMessage", errorMessage);
        return modelAndView;
    }

    @RequestMapping(value = "/login/check", method = RequestMethod.POST)
    public void login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("redirectUrl") String redirectUrl, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isEmpty(username) || isEmpty(password) || !authenticationService.isValidUser(username, password)) {
            String loginUrl = request.getContextPath() + "/login/prompt?errorMessage=Invalid Credentials";
            if (!isEmpty(redirectUrl)) {
                loginUrl += "&redirectUrl=" + redirectUrl;
            }
            response.sendRedirect(loginUrl);
        } else {
            String userCookie = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("nk-user-token", userCookie);
            cookie.setPath("/");
            response.addCookie(cookie);
            authenticationService.addCookieForUser(username, userCookie);
            if (isEmpty(redirectUrl)) {
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                response.sendRedirect(redirectUrl);
            }
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("nk-user-token", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/login/prompt";
    }
}
