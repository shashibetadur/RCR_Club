package com.rcr.service.order;

import com.rcr.domain.PurchaseOrder;
import com.rcr.repository.order.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    public void saveOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder retrieveOrder(long orderId) {
        return (PurchaseOrder) purchaseOrderRepository.get(orderId);
    }
}
