package com.rcr.service.account;

import com.rcr.domain.account.MembershipPayment;
import com.rcr.testcommon.BaseIntTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Ignore
public class AccountTransactionServiceIntTest extends BaseIntTest {

    @Autowired
    AccountTransactionService accountTransactionService;

    @Test
    public void shouldSaveMembershipTransaction() throws Exception {
        MembershipPayment membershipPayment = new MembershipPayment();
        membershipPayment.setCreditedAccountId(1);
        membershipPayment.setAmount(1000D);
        membershipPayment.setMemberId(1L);
        membershipPayment.setNotes("late");
        accountTransactionService.savePaymentDetails(membershipPayment);
        assertThat(membershipPayment.getTransactionId() > 0, is(true));
    }
}
