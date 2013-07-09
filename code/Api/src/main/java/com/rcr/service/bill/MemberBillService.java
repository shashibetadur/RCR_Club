package com.rcr.service.bill;

import com.rcr.domain.Bill;
import com.rcr.domain.BillSearchCriteria;

import java.util.List;

public interface MemberBillService {

    void saveOrder(Bill bill);

    Bill retrieveOrder(long orderId);

    List<Bill> search(BillSearchCriteria billSearchCriteria);

    List<Bill> getBillsByPaymentId(long transactionId);
}
