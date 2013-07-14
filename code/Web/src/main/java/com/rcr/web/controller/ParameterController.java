package com.rcr.web.controller;

import com.rcr.domain.Parameters;
import com.rcr.domain.member.ParameterConfiguration;
import com.rcr.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/parameter")
public class ParameterController {

    private ParameterService parameterService;

    @Autowired
    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @RequestMapping("configuration")
    public org.springframework.web.servlet.ModelAndView configuration() {
        return new ModelAndView("order/parameter/configuration","parameterConfig", parameterService.getAll());
    }

    @RequestMapping(value = "/configuration/save", method = RequestMethod.POST)
    public String saveConfiguration(ParameterConfiguration parameterConfiguration, Model model) {
        model.asMap().clear();
        parameterService.saveParameterConfigurations(parameterConfiguration);
        return "redirect:/parameter/configuration";
    }
}
