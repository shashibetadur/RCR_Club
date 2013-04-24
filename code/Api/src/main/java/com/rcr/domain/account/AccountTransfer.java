package com.rcr.domain.account;


import java.util.ArrayList;
import java.util.List;

public class AccountTransfer {

    private long fromAccountId;

    private long toAccountId;

    private double amount;

    public AccountTransfer(long fromAccountId, long toAccountId, double amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public long getFromAccountId() {
        return fromAccountId;
    }

    public long getToAccountId() {
        return toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public AccountTransaction getFromAccountTransaction() {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccount(new Account(fromAccountId));
        accountTransaction.setAmount(amount);
        accountTransaction.setCreditDebit(AccountTransaction.DEBIT);

        AccountTransactionDetail fromAccountId = new AccountTransactionDetail(CategoryTypes.Transfer.FROM_ACCOUNT_ID, String.valueOf(this.fromAccountId));
        AccountTransactionDetail toAccountId = new AccountTransactionDetail(CategoryTypes.Transfer.TO_ACCOUNT_ID, String.valueOf(this.toAccountId));
        List<AccountTransactionDetail> transactionDetails = new ArrayList<AccountTransactionDetail>();
        transactionDetails.add(fromAccountId);
        transactionDetails.add(toAccountId);
        accountTransaction.setTransactionDetails(transactionDetails);
        return accountTransaction;
    }

    public AccountTransaction getToAccountTransaction() {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccount(new Account(fromAccountId));
        accountTransaction.setAmount(amount);
        accountTransaction.setCreditDebit(AccountTransaction.CREDIT);

        AccountTransactionDetail fromAccountId = new AccountTransactionDetail(CategoryTypes.Transfer.FROM_ACCOUNT_ID, String.valueOf(this.fromAccountId));
        AccountTransactionDetail toAccountId = new AccountTransactionDetail(CategoryTypes.Transfer.TO_ACCOUNT_ID, String.valueOf(this.toAccountId));
        List<AccountTransactionDetail> transactionDetails = new ArrayList<AccountTransactionDetail>();
        transactionDetails.add(fromAccountId);
        transactionDetails.add(toAccountId);
        accountTransaction.setTransactionDetails(transactionDetails);
        return accountTransaction;
    }
}
