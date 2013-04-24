package com.rcr.domain.account;

import com.rcr.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderPayment extends Payment {


    private long purchaseOrderId;

    public PurchaseOrderPayment() {

    }

    public PurchaseOrderPayment(AccountTransaction accountTransaction) {
        super(accountTransaction.getId(), accountTransaction.getAccount().getId(), accountTransaction.getAmount(), accountTransaction.getNotes());
        this.purchaseOrderId = Long.parseLong(accountTransaction.getTransactionDetails().get(0).getValue());
    }

    public long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    @Override
    protected List<AccountTransactionDetail> buildTransactionDetails() {
        ArrayList<AccountTransactionDetail> accountTransactionDetails = new ArrayList<AccountTransactionDetail>();
        accountTransactionDetails.add(new AccountTransactionDetail(CategoryTypes.PurchaseOrder.PURCHASE_ORDER_ID, String.valueOf(getPurchaseOrderId())));
        return accountTransactionDetails;
    }

    @Override
    protected Character creditDebitFlag() {
        return AccountTransaction.DEBIT;
    }
}
