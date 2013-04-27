package com.rcr.web.controller.member;

import com.rcr.domain.Authorize;
import com.rcr.domain.Operation;
import com.rcr.domain.account.Account;
import com.rcr.domain.account.MembershipPayment;
import com.rcr.service.account.AccountService;
import com.rcr.service.account.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/member/membership/payment")
public class MembershipPaymentController {


    private AccountTransactionService accountTransactionService;
    private AccountService accountService;

    @Autowired
    public MembershipPaymentController(AccountTransactionService accountTransactionService, AccountService accountService) {
        this.accountTransactionService = accountTransactionService;
        this.accountService = accountService;
    }

    @RequestMapping(value = "/createForm/{memberId}", method = RequestMethod.GET)
    @Authorize(Operation.MEMBERSHIP_RENEW)
    public ModelAndView membershipPaymentCreateForm(@PathVariable("memberId") long memberId) {
        MembershipPayment membershipPayment = new MembershipPayment();
        membershipPayment.setMemberId(memberId);
        return new ModelAndView("member/membership/payment/create", "membershipPayment", membershipPayment);
    }

    @RequestMapping(value = "/editForm/{id}", method = RequestMethod.GET)
    @Authorize(Operation.MEMBERSHIP_RENEW)
    public ModelAndView membershipPaymentEditForm(@PathVariable("id") long id) {
        return new ModelAndView("member/membership/payment/edit", "membershipPayment", accountTransactionService.getPaymentDetails(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Authorize(Operation.MEMBERSHIP_RENEW)
    public String saveMembershipPayment(MembershipPayment membershipPayment, Model model) {
        model.asMap().clear();
        accountTransactionService.savePaymentDetails(membershipPayment);
        return "redirect:/member/viewForm/" + membershipPayment.getMemberId();
    }

    @ModelAttribute("accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
