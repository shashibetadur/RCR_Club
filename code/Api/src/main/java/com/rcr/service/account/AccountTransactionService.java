package com.rcr.service.account;


import com.rcr.domain.account.*;

import java.util.List;

public interface AccountTransactionService {

    public void savePaymentDetails(Payment payment);

    public Payment getPaymentDetails(long transactionId);

    public void transferAmount(AccountTransfer accountTransfer);

    List<ExpensePayment> getExpensePaymentDetails(ExpenseSearchCriteria expenseSearchCriteria);
}
