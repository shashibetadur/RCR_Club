package com.rcr.domain.account;

import java.util.ArrayList;
import java.util.List;

public class ExpensePayment extends Payment {


    private long expenseId;

    public ExpensePayment() {

    }

    public ExpensePayment(AccountTransaction accountTransaction) {
        super(accountTransaction.getId(), accountTransaction.getAccount().getId(), accountTransaction.getAmount(), accountTransaction.getNotes());
        this.expenseId = Long.parseLong(accountTransaction.getTransactionDetails().get(0).getValue());
    }

    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }

    @Override
    protected List<AccountTransactionDetail> buildTransactionDetails() {
        ArrayList<AccountTransactionDetail> accountTransactionDetails = new ArrayList<AccountTransactionDetail>();
        accountTransactionDetails.add(new AccountTransactionDetail(CategoryTypes.Expense.EXPENSE_ID, String.valueOf(getExpenseId())));
        return accountTransactionDetails;
    }

    @Override
    protected Character creditDebitFlag() {
        return AccountTransaction.DEBIT;
    }
}
