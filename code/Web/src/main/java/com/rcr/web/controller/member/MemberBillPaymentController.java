package com.rcr.web.controller.member;

import com.rcr.domain.Authorize;
import com.rcr.domain.Bill;
import com.rcr.domain.BillSearchCriteria;
import com.rcr.domain.Operation;
import com.rcr.domain.account.Account;
import com.rcr.domain.account.MemberBillPayment;
import com.rcr.service.account.AccountService;
import com.rcr.service.account.AccountTransactionService;
import com.rcr.service.bill.MemberBillService;
import com.rcr.web.model.MemberBillForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bill/payment")
public class MemberBillPaymentController {

    private AccountTransactionService accountTransactionService;
    private AccountService accountService;
    private MemberBillService memberBillService;

    @Autowired
    public MemberBillPaymentController(AccountTransactionService accountTransactionService, AccountService accountService, MemberBillService memberBillService) {
        this.accountTransactionService = accountTransactionService;
        this.accountService = accountService;
        this.memberBillService = memberBillService;
    }

    @RequestMapping(value = "/createBillPayment/{memberId}", method = RequestMethod.GET)
    public ModelAndView memberBillPaymentCreateForm(@PathVariable("memberId") long memberId) {
        MemberBillPayment memberBillPayment = new MemberBillPayment();
        memberBillPayment.setMemberId(memberId);
        BillSearchCriteria billSearchCriteria = new BillSearchCriteria();
        billSearchCriteria.setMemberId(memberId);
        List<Bill> billList = memberBillService.search(billSearchCriteria);
        List<MemberBillForm> memberBillList = new ArrayList<MemberBillForm>();
        for (Bill bill : billList) {
            MemberBillForm memberBillForm = new MemberBillForm();
            memberBillForm.buildDisplayBill(bill);
            memberBillList.add(memberBillForm);
        }
        ModelAndView modelAndView = new ModelAndView("bill/payment/create", "memberBillPayment", memberBillPayment);
        modelAndView.getModelMap().put("memberBillList", memberBillList);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMemberBillPayment(MemberBillPayment memberBillPayment, Model model) {
        model.asMap().clear();
        accountTransactionService.savePaymentDetails(memberBillPayment);
        return "redirect:/member/viewForm/" + memberBillPayment.getMemberId();
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
