package com.rcr.service.account;


import com.rcr.domain.account.Account;

import java.util.List;

public interface AccountService {

    public void saveAccountDetails(Account account);

    public Account getAccount(long accountId);

    public void deleteAccount(Account account);

    public List<Account> getAllAccounts();

}
