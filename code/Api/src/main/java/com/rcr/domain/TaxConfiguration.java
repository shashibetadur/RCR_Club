package com.rcr.domain;


import java.util.ArrayList;
import java.util.List;

public class TaxConfiguration {

    List<Tax> taxList = new ArrayList<Tax>();

    public TaxConfiguration() {
    }

    public TaxConfiguration(List<Tax> taxList) {
        this.taxList.addAll(taxList);
    }

    public List<Tax> getTaxList() {
        return taxList;
    }

    public List<Tax> filterBy(String  orderType) {
        List<Tax> list = new ArrayList<Tax>();
        for (Tax tax : taxList) {
            if (tax.getOrderType().equals(orderType)) {
                list.add(tax);
            }
        }
        return list;
    }
}