package com.rcr.web.controller;


import com.rcr.domain.*;
import com.rcr.service.bill.MemberBillService;
import com.rcr.service.order.PurchaseOrderService;
import com.rcr.web.model.MemberBillForm;
import com.rcr.web.model.ProcessOrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    private MemberBillService memberBillService;
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    public HomeController(MemberBillService memberBillService, PurchaseOrderService purchaseOrderService) {
        this.memberBillService = memberBillService;
        this.purchaseOrderService = purchaseOrderService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @Authorize(value = {Operation.HOME_PAGE})
    public ModelAndView addGroup() {
        BillSearchCriteria billSearchCriteria = new BillSearchCriteria();
        OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria();

        billSearchCriteria.getBillStatusList().add("NEW");
        List<Bill> billList = memberBillService.search(billSearchCriteria);
        List<MemberBillForm> memberBillList = new ArrayList<MemberBillForm>();
        for (Bill bill : billList) {
            MemberBillForm memberBillForm = new MemberBillForm();
            memberBillForm.buildDisplayBill(bill);
            memberBillList.add(memberBillForm);
        }

        orderSearchCriteria.getOrderStatusList().add('P');
        orderSearchCriteria.getOrderStatusList().add('F');
        List<PurchaseOrder> orderList = purchaseOrderService.search(orderSearchCriteria);
        List<ProcessOrderForm> orderFormList = new ArrayList<ProcessOrderForm>();
        for(PurchaseOrder order: orderList)  {
            ProcessOrderForm processOrderForm = new ProcessOrderForm();
            processOrderForm.buildDisplayOrder(order);
            orderFormList.add(processOrderForm);
        }

        ModelAndView modelAndView = new ModelAndView("home", "memberBillList", memberBillList);
        modelAndView.getModelMap().put("orderFormList", orderFormList);

        return modelAndView;
    }
}
