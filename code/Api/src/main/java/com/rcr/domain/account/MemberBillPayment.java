package com.rcr.domain.account;

import com.rcr.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class MemberBillPayment extends Payment {


    private long memberId;

    private double graceAmount;

    public MemberBillPayment() {

    }

    public MemberBillPayment(AccountTransaction accountTransaction) {
        super(accountTransaction);
        this.memberId = Long.parseLong(accountTransaction.getTransactionDetails().get(0).getValue());
        this.graceAmount = Double.parseDouble(accountTransaction.getTransactionDetails().get(1).getValue());
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
        accountTransactionDetails.add(new AccountTransactionDetail(CategoryTypes.MemberBill.MEMBER_ID, String.valueOf(getMemberId())));
        accountTransactionDetails.add(new AccountTransactionDetail(CategoryTypes.MemberBill.GRACE_AMOUNT, String.valueOf(getGraceAmount())));
        return accountTransactionDetails;
    }

    @Override
    protected Character creditDebitFlag() {
        return AccountTransaction.CREDIT;
    }

    public double getGraceAmount() {
        return graceAmount;
    }

    public void setGraceAmount(double graceAmount) {
        this.graceAmount = graceAmount;
    }
}
