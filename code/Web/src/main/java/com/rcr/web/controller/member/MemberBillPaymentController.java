package com.rcr.web.controller.member;

import com.rcr.domain.Authorize;
import com.rcr.domain.Bill;
import com.rcr.domain.BillSearchCriteria;
import com.rcr.domain.Operation;
import com.rcr.domain.account.Account;
import com.rcr.domain.account.MemberBillPayment;
import com.rcr.domain.member.Member;
import com.rcr.service.account.AccountService;
import com.rcr.service.account.AccountTransactionService;
import com.rcr.service.bill.MemberBillService;
import com.rcr.service.member.MemberService;
import com.rcr.service.member.MembershipService;
import com.rcr.service.member.MembershipServiceImpl;
import com.rcr.web.model.MemberBillForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bill/payment")
public class MemberBillPaymentController {

    private AccountTransactionService accountTransactionService;
    private AccountService accountService;
    private MemberBillService memberBillService;

    private List<MemberBillForm> memberBillList = new ArrayList<MemberBillForm>();
    private MembershipService membershipService;
    private MemberService memberService;

    @Autowired
    public MemberBillPaymentController(AccountTransactionService accountTransactionService, AccountService accountService, MemberBillService memberBillService, MembershipService membershipService, MemberService memberService) {
        this.accountTransactionService = accountTransactionService;
        this.accountService = accountService;
        this.memberBillService = memberBillService;
        this.membershipService = membershipService;
        this.memberService = memberService;
    }

    @RequestMapping(value = "/createBillPayment/{memberId}", method = RequestMethod.GET)
    public ModelAndView memberBillPaymentCreateForm(@PathVariable("memberId") long memberId) {
        MemberBillPayment memberBillPayment = new MemberBillPayment();
        memberBillPayment.setMemberId(memberId);
        BillSearchCriteria billSearchCriteria = new BillSearchCriteria();
        billSearchCriteria.getBillStatusList().add("CLOSED");
        billSearchCriteria.setMemberId(memberId);
        List<Bill> billList = memberBillService.search(billSearchCriteria);
        double totalBillAmount = 0;
        double graceAmount = 0;
        memberBillList.clear();
        MemberBillPayment memberLastBillPayment = membershipService.getMemberBillLastPaymentDetails(memberId);
        if(memberLastBillPayment == null)
            graceAmount = 0.0;
        else
            graceAmount = memberLastBillPayment.getGraceAmount();
        for (Bill bill : billList) {
            MemberBillForm memberBillForm = new MemberBillForm();
            memberBillForm.buildDisplayBill(bill);
            totalBillAmount += memberBillForm.getTotalAmount();
            memberBillList.add(memberBillForm);
        }
        ModelAndView modelAndView = new ModelAndView("bill/payment/create", "memberBillPayment", memberBillPayment);
        modelAndView.getModelMap().put("memberBillList", memberBillList);
        modelAndView.getModelMap().put("totalBillAmount", totalBillAmount - graceAmount);
        modelAndView.getModelMap().put("graceAmount", graceAmount);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveMemberBillPayment(MemberBillPayment memberBillPayment, Model model) {
        model.asMap().clear();
        List<Bill> saveBills = new ArrayList<Bill>();
        double paymentAmount = memberBillPayment.getAmount();
        double graceAmount = 0.0;
        MemberBillPayment memberLastBillPayment = membershipService.getMemberBillLastPaymentDetails(memberBillPayment.getMemberId());
        if(memberLastBillPayment == null)
                    graceAmount = 0.0;
                else
                    graceAmount = memberLastBillPayment.getGraceAmount();

        paymentAmount += graceAmount;
        for (MemberBillForm memberBillForm : memberBillList) {
            if(memberBillForm.getTotalAmount() <= paymentAmount){
                Bill bill = memberBillService.retrieveOrder(memberBillForm.getId());
                bill.setStatus("PAID");
                memberBillForm.setBillStatus("PAID");
                paymentAmount -= memberBillForm.getTotalAmount();
                saveBills.add(bill);
            }
        }
        memberBillPayment.setGraceAmount(paymentAmount);
        accountTransactionService.savePaymentDetails(memberBillPayment);
        for (Bill bill: saveBills){
            bill.setTransactionId(memberBillPayment.getTransactionId());
            memberBillService.saveOrder(bill);
        }
        ModelAndView modelAndView = new ModelAndView("bill/payment/viewStatus", "memberBillPayment", memberBillPayment);
        modelAndView.getModelMap().put("memberBillList", memberBillList);
        modelAndView.getModelMap().put("graceAmount", paymentAmount);
        return modelAndView;
    }

    @RequestMapping(value = "/searchPayment/{memberId}", method = RequestMethod.GET)
    public ModelAndView searchPayment(@PathVariable("memberId") long memberId) {
        Member member = memberService.getMemberDetails(memberId);
        return new ModelAndView("bill/searchPayment","memberDetails",member);
    }

    @RequestMapping(value = "/fetchBillsForPayment/{transactionId}", method = RequestMethod.GET)
    public ModelAndView fetchBillsForPayment(@PathVariable("transactionId") long transactionId) {
        List<Bill> billList = memberBillService.getBillsByPaymentId(transactionId);
        List<MemberBillForm> memberBillList = new ArrayList<MemberBillForm>();
        for (Bill bill : billList) {
            MemberBillForm memberBillForm = new MemberBillForm();
            memberBillForm.buildDisplayBill(bill);
            memberBillList.add(memberBillForm);
        }
        return new ModelAndView("bill/searchResultsForPayment", "memberBillList", memberBillList);
    }

    @RequestMapping(value = "/searchPaymentOfMember/", method = RequestMethod.GET)
    public ModelAndView searchPaymentOfMember(@RequestParam(value = "fromDate", required = true) Date fromDate, @RequestParam(value = "toDate", required = true) Date toDate,@RequestParam(value = "memberId", required = false) long memberId) {
        List<MemberBillPayment> memberBillPayments = membershipService.getMemberBillPayments(memberId,fromDate,toDate);
        return new ModelAndView("bill/paymentResults","memberBillPayments", memberBillPayments);
    }

    @RequestMapping(value = "/editForm/{id}", method = RequestMethod.GET)
    public ModelAndView memberBillPaymentEditForm(@PathVariable("id") long id) {
        return new ModelAndView("bill/payment/edit", "memberBillPayment", accountTransactionService.getPaymentDetails(id));
    }

    @ModelAttribute("accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
