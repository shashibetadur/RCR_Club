package com.rcr.repository.account;

import com.rcr.domain.account.AccountTransaction;
import com.rcr.domain.account.ExpenseSearchCriteria;
import com.rcr.testcommon.BaseIntTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Ignore
public class AccountTransactionRepositoryIntTest extends BaseIntTest {

    @Autowired
    AccountTransactionRepository accountTransactionRepository;

    @Test
    public void shouldGetMemberPaymentTransactionDetails() {
        List<AccountTransaction> transactionDetails = accountTransactionRepository.getMemberPaymentTransactionDetails(1);
        System.out.println(transactionDetails.size());
    }

    @Test
    public void shouldGetExpensePaymentTransactionDetails() {
        List<AccountTransaction> transactionDetails = accountTransactionRepository.getAllTransactionsFor(new ExpenseSearchCriteria("", null, new GregorianCalendar(2013, Calendar.JULY, 19).getTime()));
        System.out.println("1----------------------------------->" + transactionDetails.size());
    }


}
