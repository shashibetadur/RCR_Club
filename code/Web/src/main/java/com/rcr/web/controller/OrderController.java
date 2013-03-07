package com.rcr.web.controller;


import com.rcr.domain.OrderStatus;
import com.rcr.web.model.ProcessOrderForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    public ModelAndView orderCreationForm() {

        ProcessOrderForm processOrderForm = new ProcessOrderForm();
        return new ModelAndView("order/createOrder", "processOrderForm", processOrderForm);
    }

    @ModelAttribute("orderStates")
    public Map<Character, String> orderStates() {
        Map<Character, String> orderStates = new HashMap<Character, String>();
        for (OrderStatus orderStatus : OrderStatus.values()) {
            orderStates.put(orderStatus.getCode(), orderStatus.toString());
        }
        return orderStates;
    }
}
