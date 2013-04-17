package com.rcr.service.order;

import com.rcr.domain.OrderSearchCriteria;
import com.rcr.domain.PurchaseOrder;
import com.rcr.repository.order.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<PurchaseOrder> search(OrderSearchCriteria orderSearchCriteria) {

        List<PurchaseOrder> orderList = new ArrayList<PurchaseOrder>();
        if (orderSearchCriteria.hasOrderId()) {
            PurchaseOrder order = retrieveOrder(Long.valueOf(orderSearchCriteria.getOrderId()));
            if (order != null) orderList.add(order);
            return orderList;
        }
        List<PurchaseOrder> orders = purchaseOrderRepository.findBy(orderSearchCriteria);
        orderList.addAll(orders);
        return orderList;
    }
}
