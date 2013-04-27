package com.rcr.domain.account;

import java.util.Date;
import java.util.List;

public abstract class Payment {
    private long transactionId;

    private long accountId;

    private String accountName;

    private double amount;

    private Date date;

    private String notes;

    protected Payment() {
    }

    public Payment(AccountTransaction accountTransaction) {
        this.transactionId = accountTransaction.getId();
        this.accountId = accountTransaction.getAccount().getId();
        this.amount = accountTransaction.getAmount();
        this.notes = accountTransaction.getNotes();
        this.date = accountTransaction.getTransactionDate();
        this.accountName = accountTransaction.getAccount().getName();
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountTransaction buildAccountTransaction() {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setId(getTransactionId());
        accountTransaction.setAccount(new Account(getAccountId()));
        accountTransaction.setAmount(getAmount());
        accountTransaction.setCreditDebit(creditDebitFlag());
        accountTransaction.setNotes(getNotes());
        accountTransaction.getTransactionDetails().addAll(buildTransactionDetails());
        return accountTransaction;
    }

    protected abstract Character creditDebitFlag();

    protected abstract List<AccountTransactionDetail> buildTransactionDetails();

}
