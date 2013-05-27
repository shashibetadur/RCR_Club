package com.rcr.web.controller;

import com.rcr.domain.Inventory;
import com.rcr.service.inventory.InventoryService;
import com.rcr.web.model.ProcessOrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping(value = "/currentStock", method = RequestMethod.GET)
    public ModelAndView currentStock() {
        List<Inventory> inventoryList = inventoryService.getCurrentStock();

        return new ModelAndView("inventory/currentStock", "inventoryList", inventoryList);
    }

    @RequestMapping(value = "/updateStock", method = RequestMethod.GET)
    public ModelAndView updateStock() {

        ProcessOrderForm processOrderForm = new ProcessOrderForm();
        return new ModelAndView("inventory/updateStock","processOrderForm",processOrderForm);
    }
}
