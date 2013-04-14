package com.rcr.web.controller;

import com.rcr.domain.Bill;
import com.rcr.service.bill.MemberBillService;
import com.rcr.service.member.MemberService;
import com.rcr.web.model.MemberBillForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bill")
public class BillController {

    MemberBillService memberBillService;

    MemberService memberService;

    @Autowired
    public BillController(MemberBillService memberBillService, MemberService memberService) {
        this.memberBillService = memberBillService;
        this.memberService = memberService;
    }

    @RequestMapping(value = "/createBill", method = RequestMethod.GET)
    public ModelAndView orderCreationForm(@RequestParam(value = "memberId", defaultValue = "0") long memberId) {

        MemberBillForm memberBillForm = new MemberBillForm();
        memberBillForm.setMember(memberService.getMemberDetails(memberId));
        return new ModelAndView("bill/createBill", "memberBillForm", memberBillForm);
    }

    @RequestMapping(value = "/saveBill", method = RequestMethod.POST)
    public String saveOrder(MemberBillForm memberBillForm, Model model) {
        model.asMap().clear();
        Bill bill = memberBillForm.buildOrder();
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
}
