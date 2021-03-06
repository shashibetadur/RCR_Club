package com.rcr.domain.account;

import com.rcr.domain.Category;
import com.rcr.domain.CategoryAttribute;

import java.util.ArrayList;
import java.util.List;

public class MembershipPayment extends Payment {


    private long memberId;

    public MembershipPayment() {

    }

    public MembershipPayment(AccountTransaction accountTransaction) {
        super(accountTransaction);
        this.memberId = Long.parseLong(accountTransaction.getTransactionDetails().get(0).getValue());
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    @Override
    protected List<AccountTransactionDetail> buildTransactionDetails() {
        ArrayList<AccountTransactionDetail> accountTransactionDetails = new ArrayList<AccountTransactionDetail>();
        accountTransactionDetails.add(new AccountTransactionDetail(CategoryTypes.MembershipFees.MEMBER_ID, String.valueOf(getMemberId())));
        return accountTransactionDetails;
    }

    @Override
    protected Character creditDebitFlag() {
        return AccountTransaction.CREDIT;
    }
}
