package com.rcr.service.account;

import com.rcr.domain.account.Account;
import com.rcr.testcommon.BaseIntTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountServiceIntTest extends BaseIntTest {

    @Autowired
    AccountService accountService;

    @Test
    public void shouldVerifyAccountCrud() throws Exception {
        Account account = new Account();
        account.setAccountNumber("100");
        account.setName("ICICI");

        accountService.saveAccountDetails(account);
        assertThat(account.getId() > 0, is(true));

        Account accountDb = accountService.getAccount(account.getId());
        assertThat(accountDb.getId(), is(account.getId()));

        accountDb.setAccountNumber("1000");
        accountService.saveAccountDetails(accountDb);
        accountDb = accountService.getAccount(accountDb.getId());
        assertThat(accountDb.getAccountNumber(), is("1000"));
    }
}
