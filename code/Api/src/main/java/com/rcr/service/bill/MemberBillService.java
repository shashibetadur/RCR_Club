package com.rcr.service.bill;

import com.rcr.domain.Bill;

public interface MemberBillService {

    void saveOrder(Bill bill);

    Bill retrieveOrder(long orderId);
}
