package com.rcr.web;


import com.rcr.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

import static org.apache.commons.lang.StringUtils.isEmpty;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private UserRepository userRepository;

    @Autowired
    public AuthorizationInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI().toLowerCase();
        String contextPath = request.getContextPath().toLowerCase();
        String loginUrl = contextPath + "/login/prompt";
        String loginCheck = contextPath + "/login/check";
        String logoutUrl = contextPath + "/logout";

        if (requestURI.endsWith(".css") || requestURI.endsWith(".js") || requestURI.endsWith(".png") || requestURI.endsWith(".jpg") || requestURI.endsWith(".jpeg")) {
            return super.preHandle(request, response, handler);
        }

        if (requestURI.startsWith(loginUrl) || requestURI.startsWith(loginCheck) || requestURI.startsWith(logoutUrl)) {
            return super.preHandle(request, response, handler);
        }
        String userToken = resolveToken(request);
        if (userRepository.inValidToken(userToken)) {
            String redirectUrl = isEmpty(request.getQueryString()) ? requestURI : requestURI + "?" + request.getQueryString();
            response.sendRedirect(loginUrl + "?redirectUrl=" + URLEncoder.encode(redirectUrl, "UTF-8"));
            return false;
        }
        User user = userRepository.getUserByToken(userToken);
        request.getSession().setAttribute("user", user);
        if (handler instanceof HandlerMethod && isUserAuthorized((HandlerMethod) handler, user)) {
            super.preHandle(request, response, handler);
            return true;
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    private boolean isUserAuthorized(HandlerMethod handler, User user) {
        Operation operations = handler.getMethod().getAnnotation(Operation.class);
        if (operations == null || operations.value() == null || operations.value().length < 1) return true;
        String[] value = operations.value();
        for (String operation : value) {
            if (user.canPerform(operation)) return true;
        }
        return false;
    }

    private String resolveToken(HttpServletRequest request) {
        String userToken = "";
        if (request.getCookies() != null && request.getCookies().length > 0) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("nk-user-token")) {
                    userToken = cookie.getValue();
                }
            }
        }
        return userToken;
    }
}
