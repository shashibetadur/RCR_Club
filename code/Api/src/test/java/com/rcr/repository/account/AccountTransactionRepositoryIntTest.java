package com.rcr.repository.account;

import com.rcr.domain.account.AccountTransaction;
import com.rcr.testcommon.BaseIntTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
