package com.rcr.service.bill;

import com.rcr.domain.Bill;
import com.rcr.repository.bill.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberBillServiceImpl implements MemberBillService {

    BillRepository billRepository;

    @Autowired
    public MemberBillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public void saveOrder(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public Bill retrieveOrder(long orderId) {
        return (Bill) billRepository.get(orderId);
    }
}
