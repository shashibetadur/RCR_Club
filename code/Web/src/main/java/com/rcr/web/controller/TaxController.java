package com.rcr.web.controller;

import com.rcr.domain.TaxConfiguration;
import com.rcr.service.order.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tax")
public class TaxController {

    private PurchaseOrderService purchaseOrderService;

    protected TaxController() {
    }

    @Autowired
    public TaxController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @RequestMapping("/order/configuration")
    public org.springframework.web.servlet.ModelAndView configuration() {
        return new ModelAndView("order/configuration", "taxConfiguration", purchaseOrderService.getTaxConfiguration());
    }

    @RequestMapping(value = "/configuration/save", method = RequestMethod.POST)
    public String saveConfiguration(TaxConfiguration taxConfiguration, Model model) {
        model.asMap().clear();
        purchaseOrderService.saveTaxConfigurations(taxConfiguration);
        return "redirect:/tax/order/configuration";
    }

    @ModelAttribute("orderTypes")
    public List<String> orderTypes() {
        return Arrays.asList("Order", "Bill");
    }
}
