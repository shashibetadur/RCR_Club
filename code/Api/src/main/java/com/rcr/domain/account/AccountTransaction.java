package com.rcr.domain.account;

import com.rcr.domain.Entity;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AccountTransaction extends Entity {

    public static final Character CREDIT = 'C';

    public static final Character DEBIT = 'D';

    private Account account;

    private Date transactionDate;

    private double amount;

    private String notes;

    private Character creditDebit;

    private List<AccountTransactionDetail> transactionDetails = new ArrayList<AccountTransactionDetail>();


    public AccountTransaction() {
        transactionDate = Calendar.getInstance().getTime();
    }

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


    public Payment buildPayment() {
        try {
            if (transactionDetails.isEmpty()) return null;
            Class<? extends Payment> paymentType = CategoryTypes.getPaymentType(transactionDetails.get(0));
            Constructor<? extends Payment> constructor = paymentType.getConstructor(AccountTransaction.class);
            return constructor.newInstance(new Object[]{this});
        } catch (Exception e) {
            throw new RuntimeException("could not build payment out transaction" + id);
        }
    }
}
