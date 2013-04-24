package com.rcr.service.account;


import com.rcr.domain.account.Account;
import com.rcr.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void saveAccountDetails(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account getAccount(long accountId) {
        return accountRepository.get(accountId);
    }

    @Override
    public void deleteAccount(Account account) {
        Account accountFromDb = accountRepository.get(account.getId());
        accountFromDb.setDeleted(true);
        accountRepository.save(accountFromDb);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAllActive();
    }
}
