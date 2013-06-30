package com.rcr.web.controller;


import com.rcr.common.DateUtils;
import com.rcr.domain.Inventory;
import com.rcr.domain.OrderStatus;
import com.rcr.domain.PurchaseOrder;
import com.rcr.domain.PurchaseOrderDetail;
import com.rcr.service.inventory.InventoryService;
import com.rcr.service.order.PurchaseOrderService;
import com.rcr.domain.OrderSearchCriteria;
import com.rcr.web.model.ProcessOrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    private PurchaseOrderService purchaseOrderService;
    private InventoryService inventoryService;

    @Autowired
    public OrderController(PurchaseOrderService purchaseOrderService, InventoryService inventoryService) {
        this.purchaseOrderService = purchaseOrderService;
        this.inventoryService = inventoryService;
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    public ModelAndView orderCreationForm() {

        ProcessOrderForm processOrderForm = new ProcessOrderForm();
        ModelAndView modelAndView = new ModelAndView("order/createOrder", "processOrderForm", processOrderForm);
        modelAndView.getModelMap().put("taxConfigurations", purchaseOrderService.getTaxConfiguration().filterBy("Order"));
        return modelAndView;
    }

    @RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
    public ModelAndView orderSearchForm() {
        return new ModelAndView("order/searchOrder");
    }

    @RequestMapping(value = "/searchForm", method = RequestMethod.POST)
    public ModelAndView orderSearchQuery(OrderSearchCriteria orderSearchCriteria) {
        List<PurchaseOrder> orderList = purchaseOrderService.search(orderSearchCriteria);
        List<ProcessOrderForm> orderFormList = new ArrayList<ProcessOrderForm>();
        for(PurchaseOrder order: orderList)  {
            ProcessOrderForm processOrderForm = new ProcessOrderForm();
            processOrderForm.buildDisplayOrder(order);
            orderFormList.add(processOrderForm);
        }
        return new ModelAndView("order/searchResults", "orderFormList", orderFormList);
    }

    @ModelAttribute("orderStates")
    public Map<Character, String> orderStates() {
        Map<Character, String> orderStates = new HashMap<Character, String>();
        for (OrderStatus orderStatus : OrderStatus.values()) {
            orderStates.put(orderStatus.getCode(), orderStatus.toString());
        }
        return orderStates;
    }

    @RequestMapping(value = "/delete/{orderId}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("orderId") long orderId) {

        PurchaseOrder purchaseOrder = purchaseOrderService.retrieveOrder(orderId);
        purchaseOrder.prepareForDeletion();
        purchaseOrderService.saveOrder(purchaseOrder);
        return "redirect:/order/viewOrder/" + orderId;
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrder(ProcessOrderForm processOrderForm, Model model) {
        model.asMap().clear();
        PurchaseOrder purchaseOrder = processOrderForm.buildOrder(purchaseOrderService.getTaxConfiguration().filterBy("Order"));
        if (purchaseOrder.getStatus() == OrderStatus.RECEIVED.getCode()) {
            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrder.getPurchaseOrderDetails()) {
                Inventory inventory = new Inventory();
                inventory.setMaterial(purchaseOrderDetail.getMaterial());
                inventory.setDate(purchaseOrder.getDate());
                inventory.setEntryDate(DateUtils.currentDate());
                inventory.setCurrentStock(inventoryService.getMaterialQty(purchaseOrderDetail.getMaterial().getId())
                        + purchaseOrderDetail.getQuantity());
                inventory.setQuantity(purchaseOrderDetail.getQuantity());
                inventory.setCreditDebit('C');
                inventoryService.saveInventory(inventory);
                purchaseOrderDetail.setInventoryId(inventory.getId());
            }
        }

        purchaseOrderService.saveOrder(purchaseOrder);
        return "redirect:/order/viewOrder/" + purchaseOrder.getId();
    }

    @RequestMapping(value = "/viewOrder/{orderId}", method = RequestMethod.GET)
    public ModelAndView viewOrder(@PathVariable("orderId") long orderId) {
        PurchaseOrder purchaseOrder = purchaseOrderService.retrieveOrder(orderId);
        ProcessOrderForm processOrderForm = new ProcessOrderForm();
        processOrderForm.buildDisplayOrder(purchaseOrder);
        return new ModelAndView("order/viewOrder", "order", processOrderForm);
    }

    @RequestMapping(value = "/orderEditForm/{orderId}", method = RequestMethod.GET)
    public ModelAndView orderEditForm(@PathVariable("orderId") long orderId) {
        PurchaseOrder purchaseOrder = purchaseOrderService.retrieveOrder(orderId);
        ProcessOrderForm processOrderForm = new ProcessOrderForm();
        processOrderForm.buildDisplayOrder(purchaseOrder);
        if(processOrderForm.getDeleteFlag() == null)
            return new ModelAndView("order/editOrder", "processOrderForm", processOrderForm);
        else
            return new ModelAndView("order/viewOrder", "order", processOrderForm);
    }
}
