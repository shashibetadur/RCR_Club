package com.rcr.web.controller;


import com.rcr.domain.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @Operation(value = {Operation.HOME_PAGE})
    public String addGroup() {
        return "home";
    }
}
