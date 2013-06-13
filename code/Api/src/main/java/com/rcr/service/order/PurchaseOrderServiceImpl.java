package com.rcr.service.order;

import com.rcr.domain.OrderSearchCriteria;
import com.rcr.domain.PurchaseOrder;
import com.rcr.domain.Tax;
import com.rcr.domain.TaxConfiguration;
import com.rcr.repository.order.PurchaseOrderRepository;
import com.rcr.repository.tax.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    private PurchaseOrderRepository purchaseOrderRepository;
    private TaxRepository taxRepository;

    @Autowired
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, TaxRepository taxRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.taxRepository = taxRepository;
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

    @Override
    public TaxConfiguration getTaxConfiguration() {
        return new TaxConfiguration(taxRepository.getAllActive());
    }

    @Override
    public void saveTaxConfigurations(TaxConfiguration taxConfiguration) {
        Map<Long, Tax> taxMap = new HashMap<Long, Tax>();
        for (Tax tax : taxConfiguration.getTaxList()) {
            taxMap.put(tax.getId(), tax);
        }
        //update existing
        for (Tax tax : taxRepository.getAll()) {
            if (taxMap.containsKey(tax.getId())) {
                tax.copyFrom(taxMap.get(tax.getId()));
                taxMap.remove(tax.getId());
            } else {
                tax.setDeleteFlag('Y');
            }
            taxRepository.save(tax);
        }

        //create new
        for (Long taxId : taxMap.keySet()) {
            taxRepository.save(taxMap.get(taxId));
        }
    }
}
