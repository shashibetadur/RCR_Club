package com.rcr.domain;

public class BillTaxDetail extends Entity{

    private long billId;
    private Tax tax;
    private double percentage;
    private double taxAmount;
    private String taxType;

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
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
