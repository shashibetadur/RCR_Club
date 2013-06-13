package com.rcr.web.controller;

import com.rcr.domain.Bill;
import com.rcr.domain.BillSearchCriteria;
import com.rcr.service.bill.MemberBillService;
import com.rcr.service.member.MemberService;
import com.rcr.service.order.PurchaseOrderService;
import com.rcr.web.model.MemberBillForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {

    MemberBillService memberBillService;
    private PurchaseOrderService purchaseOrderService;

    MemberService memberService;

    @Autowired
    public BillController(MemberBillService memberBillService, PurchaseOrderService purchaseOrderService, MemberService memberService) {
        this.memberBillService = memberBillService;
        this.purchaseOrderService = purchaseOrderService;
        this.memberService = memberService;
    }

    @RequestMapping(value = "/createBill", method = RequestMethod.GET)
    public ModelAndView orderCreationForm(@RequestParam(value = "memberId", defaultValue = "0") long memberId) {

        MemberBillForm memberBillForm = new MemberBillForm();
        memberBillForm.setMember(memberService.getMemberDetails(memberId));
        ModelAndView modelAndView = new ModelAndView("bill/createBill", "memberBillForm", memberBillForm);
        modelAndView.getModelMap().put("taxConfigurations", purchaseOrderService.getTaxConfiguration().filterBy("Bill"));
        return modelAndView;
    }

    @RequestMapping(value = "/saveBill", method = RequestMethod.POST)
    public String saveOrder(MemberBillForm memberBillForm, Model model) {
        model.asMap().clear();
        Bill bill = memberBillForm.buildOrder(purchaseOrderService.getTaxConfiguration().filterBy("Bill"));
        bill.setMember(memberService.getMemberDetails(memberBillForm.getMember().getPersonalDetails().getId()));
        memberBillService.saveOrder(bill);
        return "redirect:/bill/viewBill/" + bill.getId();
    }

    @RequestMapping(value = "/viewBill/{orderId}", method = RequestMethod.GET)
    public ModelAndView viewOrder(@PathVariable("orderId") long orderId) {
        Bill bill = memberBillService.retrieveOrder(orderId);
        MemberBillForm memberBillForm = new MemberBillForm();
        memberBillForm.buildDisplayBill(bill);
        memberBillForm.setMember(memberService.getMemberDetails(bill.getMember().getPersonalDetails().getId()));
        return new ModelAndView("bill/viewBill", "bill", memberBillForm);
    }

    @RequestMapping(value = "/billEdit/{billId}", method = RequestMethod.GET)
    public ModelAndView billEditForm(@PathVariable("billId") long billId) {
        Bill bill = memberBillService.retrieveOrder(billId);
        MemberBillForm memberBillForm = new MemberBillForm();
        memberBillForm.buildDisplayBill(bill);
        memberBillForm.setMember(memberService.getMemberDetails(bill.getMember().getPersonalDetails().getId()));
        return new ModelAndView("bill/editBill", "memberBillForm", memberBillForm);
    }

    @RequestMapping(value = "/searchBill", method = RequestMethod.GET)
    public ModelAndView billSearchForm() {
        return new ModelAndView("bill/searchBill");
    }

    @RequestMapping(value = "/searchBill", method = RequestMethod.POST)
    public ModelAndView billSearchQuery(BillSearchCriteria billSearchCriteria) {
        List<Bill> billList = memberBillService.search(billSearchCriteria);
        List<MemberBillForm> memberBillList = new ArrayList<MemberBillForm>();
        for (Bill bill : billList) {
            MemberBillForm memberBillForm = new MemberBillForm();
            memberBillForm.buildDisplayBill(bill);
            memberBillList.add(memberBillForm);
        }
        return new ModelAndView("bill/searchResults", "memberBillList", memberBillList);
    }

    @ModelAttribute("billStates")
    public List<String> billStates() {
        return Arrays.asList("NEW", "CLOSED");
    }
}
