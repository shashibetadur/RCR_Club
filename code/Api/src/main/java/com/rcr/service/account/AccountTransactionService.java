package com.rcr.service.account;


import com.rcr.domain.account.AccountTransfer;
import com.rcr.domain.account.MembershipPayment;
import com.rcr.domain.account.Payment;

public interface AccountTransactionService {

    public void savePaymentDetails(Payment payment);

    public Payment getPaymentDetails(long transactionId);

    public void transferAmount(AccountTransfer accountTransfer);

}
