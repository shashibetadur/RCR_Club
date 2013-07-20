package com.rcr.domain.account;

import java.util.ArrayList;
import java.util.List;

public class ExpensePayment extends Payment {


    private String expenseType;

    public ExpensePayment() {

    }

    public ExpensePayment(AccountTransaction accountTransaction) {
        super(accountTransaction);
        this.expenseType = accountTransaction.getTransactionDetails().get(0).getValue();
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    @Override
    protected List<AccountTransactionDetail> buildTransactionDetails() {
        ArrayList<AccountTransactionDetail> accountTransactionDetails = new ArrayList<AccountTransactionDetail>();
        accountTransactionDetails.add(new AccountTransactionDetail(CategoryTypes.Expense.EXPENSE_TYPE, getExpenseType()));
        return accountTransactionDetails;
    }

    @Override
    protected Character creditDebitFlag() {
        return AccountTransaction.DEBIT;
    }
}
