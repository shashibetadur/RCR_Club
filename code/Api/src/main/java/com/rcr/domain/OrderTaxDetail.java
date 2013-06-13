package com.rcr.domain;


public class OrderTaxDetail extends Entity {

    private long orderId;
    private Tax tax;
    private double percentage;
    private double taxAmount;
    private String taxType;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTaxType() {
        return tax.getTaxType();
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }
}