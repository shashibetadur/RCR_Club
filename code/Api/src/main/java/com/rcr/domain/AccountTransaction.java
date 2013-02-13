package com.rcr.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AccountTransaction extends Entity {

    private Account account;

    private Date transactionDate;

    private double amount;

    private double balance;

    private String notes;

    private Character creditDebit;

    private List<AccountTransactionDetail> transactionDetails = new ArrayList<AccountTransactionDetail>();

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Character getCreditDebit() {
        return creditDebit;
    }

    public void setCreditDebit(Character creditDebit) {
        this.creditDebit = creditDebit;
    }

    public List<AccountTransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<AccountTransactionDetail> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
}
