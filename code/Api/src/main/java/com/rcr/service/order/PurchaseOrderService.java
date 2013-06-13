package com.rcr.service.order;

import com.rcr.domain.OrderSearchCriteria;
import com.rcr.domain.PurchaseOrder;
import com.rcr.domain.TaxConfiguration;

import java.util.List;

public interface PurchaseOrderService {

    void saveOrder(PurchaseOrder purchaseOrder);

    PurchaseOrder retrieveOrder(long orderId);

    List<PurchaseOrder> search(OrderSearchCriteria orderSearchCriteria);

    TaxConfiguration getTaxConfiguration();

    void  saveTaxConfigurations(TaxConfiguration taxConfigurations);
}
