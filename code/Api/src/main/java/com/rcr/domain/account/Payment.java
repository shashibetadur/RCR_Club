package com.rcr.domain.account;

import java.util.List;

public abstract class Payment {
    private long transactionId;

    private long creditedAccountId;

    private double amount;

    private String notes;

    protected Payment() {
    }

    public Payment(long transactionId, long creditedAccountId, double amount, String notes) {
        this.transactionId = transactionId;
        this.creditedAccountId =creditedAccountId;
        this.amount = amount;
        this.notes = notes;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getCreditedAccountId() {
        return creditedAccountId;
    }

    public void setCreditedAccountId(long creditedAccountId) {
        this.creditedAccountId = creditedAccountId;
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

    public AccountTransaction buildAccountTransaction() {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setId(getTransactionId());
        accountTransaction.setAccount(new Account(getCreditedAccountId()));
        accountTransaction.setAmount(getAmount());
        accountTransaction.setCreditDebit(creditDebitFlag());
        accountTransaction.setNotes(getNotes());
        accountTransaction.getTransactionDetails().addAll(buildTransactionDetails());
        return accountTransaction;
    }

    protected abstract Character creditDebitFlag();

    protected abstract List<AccountTransactionDetail> buildTransactionDetails();

}
