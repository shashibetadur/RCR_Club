package com.rcr.web.controller.account;


import com.rcr.domain.Authorize;
import com.rcr.domain.Operation;
import com.rcr.domain.account.Account;
import com.rcr.domain.account.ExpensePayment;
import com.rcr.domain.account.ExpenseSearchCriteria;
import com.rcr.domain.account.Payment;
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
@RequestMapping("/account/expense")
public class ExpensePaymentController {

    private AccountService accountService;
    private AccountTransactionService accountTransactionService;

    //requited for proxy creation
    protected ExpensePaymentController() {
    }

    @Autowired
    public ExpensePaymentController(AccountService accountService, AccountTransactionService accountTransactionService) {
        this.accountService = accountService;
        this.accountTransactionService = accountTransactionService;
    }

    @RequestMapping(value = "/createForm", method = RequestMethod.GET)
    @Authorize(Operation.EXPENSE_CREATE)
    public ModelAndView create() {
        ExpensePayment expensePayment = new ExpensePayment();
        return new ModelAndView("account/expense/createForm", "expensePayment", expensePayment);
    }

    @RequestMapping(value = "/editForm/{id}", method = RequestMethod.GET)
    @Authorize(Operation.EXPENSE_EDIT)
    public ModelAndView edit(@PathVariable("id") long expenseTransactionId) {
        ExpensePayment expensePayment = new ExpensePayment();
        Payment payment = accountTransactionService.getPaymentDetails(expenseTransactionId);
        return new ModelAndView("account/expense/createForm", "expensePayment", payment);
    }

    @RequestMapping(value = "/viewForm/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") long expenseTransactionId) {
        Payment payment = accountTransactionService.getPaymentDetails(expenseTransactionId);
        return new ModelAndView("account/expense/viewForm", "expensePayment", payment);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Authorize({Operation.EXPENSE_CREATE, Operation.EXPENSE_EDIT})
    public String save(ExpensePayment expensePayment, Model model) {
        model.asMap().clear();
        accountTransactionService.savePaymentDetails(expensePayment);
        return "redirect:/account/expense/viewForm/" + expensePayment.getTransactionId();
    }

    @RequestMapping(value = "/searchForm", method = RequestMethod.GET)
    public String searchForm() {
        return "account/expense/searchForm";
    }

    @RequestMapping(value = "/searchForm", method = RequestMethod.POST)
    public ModelAndView search(ExpenseSearchCriteria expenseSearchCriteria) {
        List<ExpensePayment> expensePayments = accountTransactionService.getExpensePaymentDetails(expenseSearchCriteria);
        return new ModelAndView("account/expense/searchResults", "expensePayments", expensePayments);
    }


    @ModelAttribute("accounts")
    public List<Account> accounts() {
        return accountService.getAllAccounts();
    }
}
