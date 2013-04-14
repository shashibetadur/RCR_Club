package com.rcr.domain;

import java.util.*;

public class PurchaseOrder extends Entity {

    private Date date;

    private double amount;

    private String status;

    private List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<PurchaseOrderDetail>();

    public PurchaseOrder() {
        date = Calendar.getInstance().getTime();
        status = "NEW";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusText(char code){
        return OrderStatus.getNameByCode(code);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(List<PurchaseOrderDetail> purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
    }
}
