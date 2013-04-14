package com.rcr.service.order;

import com.rcr.domain.PurchaseOrder;

public interface PurchaseOrderService {

    void saveOrder(PurchaseOrder purchaseOrder);

    PurchaseOrder retrieveOrder(long orderId);
}
