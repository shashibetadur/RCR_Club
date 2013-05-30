package com.rcr.web.controller;

import com.rcr.common.DateUtils;
import com.rcr.domain.Inventory;
import com.rcr.domain.Material;
import com.rcr.service.inventory.InventoryService;
import com.rcr.service.material.MaterialService;
import com.rcr.web.model.DisplayMaterial;
import com.rcr.web.model.ProcessOrderForm;
import com.rcr.web.model.StockBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private InventoryService inventoryService;
    private MaterialService materialService;

    @Autowired
    public InventoryController(InventoryService inventoryService, MaterialService materialService) {
        this.inventoryService = inventoryService;
        this.materialService = materialService;
    }

    @RequestMapping(value = "/currentStock", method = RequestMethod.GET)
    public ModelAndView currentStock() {
        List<Inventory> inventoryList = inventoryService.getCurrentStock();
        return new ModelAndView("inventory/currentStock", "inventoryList", inventoryList);
    }

    @RequestMapping(value = "/updateStock", method = RequestMethod.GET)
    public ModelAndView updateStock() {
        StockBasket stockBasket = new StockBasket();
        return new ModelAndView("inventory/updateStock", "stockBasket", stockBasket);
    }

    @RequestMapping(value = "/saveStock", method = RequestMethod.POST)
    public String saveStock(StockBasket stockBasket, Model model) {
        model.asMap().clear();
        for (DisplayMaterial displayMaterial : stockBasket.getDisplayMaterialList()) {
            Inventory inventory = new Inventory();
            inventory.setMaterial(materialService.getMaterialDetails(displayMaterial.getId()));
            inventory.setDate(DateUtils.currentDate());
            inventory.setEntryDate(DateUtils.currentDate());
            inventory.setCurrentStock(inventoryService.getMaterialQty(displayMaterial.getId())
                    - displayMaterial.getQty());
            inventory.setQuantity(displayMaterial.getQty());
            inventory.setCreditDebit('D');
            inventoryService.saveInventory(inventory);
        }
        return "redirect:/inventory/currentStock/";
    }
}
