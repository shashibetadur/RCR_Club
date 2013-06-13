package com.rcr.domain;

public class Tax extends Entity {
    private String taxType;
    private String description;
    private double percentage;
    private String orderType;

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void copyFrom(Tax tax) {
        this.taxType = tax.getTaxType();
        this.orderType = tax.getOrderType();
        this.percentage = tax.getPercentage();
        this.description = tax.getDescription();
    }
}
