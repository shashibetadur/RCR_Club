package com.rcr.domain.account;

import com.rcr.domain.Entity;

public class Account extends Entity {

    private String name;

    private String accountNumber;

    public Account() {
    }

    public Account(long accountId) {
        id = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
