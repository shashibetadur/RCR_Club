package com.rcr.web.controller.account;

import com.rcr.domain.Authorize;
import com.rcr.domain.Operation;
import com.rcr.domain.account.Account;
import com.rcr.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/list")
    @Authorize(Operation.ACCOUNT_LIST)
    public ModelAndView list() {
        return new ModelAndView("account/list", "accountList", accountService.getAllAccounts());
    }

    @RequestMapping("/createForm")
    @Authorize(Operation.ACCOUNT_CREATE)
    public ModelAndView createForm() {
        return new ModelAndView("account/createForm", "account", new Account());
    }

    @RequestMapping("/editForm/{id}")
    @Authorize(Operation.ACCOUNT_EDIT)
    public ModelAndView editForm(@PathVariable("id") long id) {
        return new ModelAndView("account/editForm", "account", accountService.getAccount(id));
    }

    @RequestMapping("/deleteForm/{id}")
    @Authorize(Operation.ACCOUNT_DELETE)
    public ModelAndView deleteForm(@PathVariable("id") long id) {
        return new ModelAndView("account/deleteForm", "account", accountService.getAccount(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Authorize({Operation.ACCOUNT_CREATE, Operation.ACCOUNT_EDIT})
    public
    @ResponseBody
    Account save(Account account, Model model) {
        model.asMap().clear();
        accountService.saveAccountDetails(account);
        return account;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Authorize(Operation.ACCOUNT_DELETE)
    public
    @ResponseBody
    Account delete(Account account, Model model) {
        model.asMap().clear();
        accountService.deleteAccount(account);
        return account;
    }
}
