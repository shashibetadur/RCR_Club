package com.rcr.web.controller.account;

import com.rcr.domain.account.Account;
import com.rcr.domain.account.AccountTransfer;
import com.rcr.service.account.AccountService;
import com.rcr.service.account.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
@RequestMapping("/account/transaction")
public class AccountTransactionController {

    private AccountTransactionService accountTransactionService;
    private AccountService accountService;

    @Autowired
    public AccountTransactionController(AccountTransactionService accountTransactionService, AccountService accountService) {
        this.accountTransactionService = accountTransactionService;
        this.accountService = accountService;
    }


    @RequestMapping(value = "/transferForm", method = RequestMethod.GET)
    public ModelAndView accountTransferForm() {
        return new ModelAndView("account/transaction/transferForm", "accountTransfer", new AccountTransfer());
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public String accountTransfer(AccountTransfer accountTransfer, Model model) {
        model.asMap().clear();
        accountTransactionService.transferAmount(accountTransfer);
        return "redirect:/account/list";
    }

    @ModelAttribute("accounts")
    public List<Account> accounts() {
        return accountService.getAllAccounts();
    }

}
