package com.rcr.web.model;

import com.rcr.domain.*;
import com.rcr.domain.member.Member;
import com.rcr.web.JsonSerializer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberBillForm {

    private Member member;

    private long id;

    private String billStatus;

    private List<Item> itemList = new ArrayList<Item>();

    private List<DisplayItem> displayItemList = new ArrayList<DisplayItem>();

    private List<BillTaxDetail> billTaxDetails = new ArrayList<BillTaxDetail>();

    private String itemListJason = "[]";

    private String taxListJason = "[]";

    private double totalAmount;

    private double grandTotalAmount;

    private double rounding;

    private Date billDate;

    private Character deleteFlag;

    public MemberBillForm() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemListJason() {
        return itemListJason;
    }

    public void setItemListJason(String itemListJason) {
        this.itemListJason = itemListJason;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<DisplayItem> getDisplayItemList() {
        return displayItemList;
    }

    public void setDisplayItemList(List<DisplayItem> displayItemList) {
        this.displayItemList = displayItemList;
    }

    private double roundTo2Decimals(double val) {
                DecimalFormat df2 = new DecimalFormat("###.##");
            return Double.valueOf(df2.format(val));
    }

    public Bill buildOrder(List<Tax> taxList) {

        double totalAmount = 0;
        Bill bill = new Bill();
        bill.setId(id);
        bill.setDate(this.getBillDate());
        bill.setStatus(this.getBillStatus());
        for (DisplayItem displayItem : displayItemList) {
            BillDetail billDetail = new BillDetail();
            billDetail.setItem(new com.rcr.domain.Item(displayItem.getId()));
            billDetail.setQuantity(displayItem.getQty());
            bill.getBillDetails().add(billDetail);
            totalAmount += (displayItem.getPrice() * displayItem.getQty());
        }

        double taxAmount = 0;
        for (Tax tax : taxList) {
            double taxValue = 0;
            BillTaxDetail billTaxDetail = new BillTaxDetail();
            billTaxDetail.setTax(tax);
            billTaxDetail.setPercentage(tax.getPercentage());
            taxValue = (totalAmount * tax.getPercentage()) / 100;
            taxValue = roundTo2Decimals(taxValue);
            billTaxDetail.setTaxAmount(taxValue);
            taxAmount += taxValue;
            bill.getBillTaxDetails().add(billTaxDetail);
        }
        taxAmount =  roundTo2Decimals(taxAmount);
        if((taxAmount - Math.floor(taxAmount)) >= .5)
            taxAmount = Math.ceil(taxAmount);
        else
            taxAmount = Math.floor(taxAmount);
        totalAmount += taxAmount;
        bill.setAmount(totalAmount);
        return bill;
    }

    public void buildDisplayBill(Bill bill) {
        double totalAmount = 0;
        this.setId(bill.getId());
        this.setBillStatus(bill.getStatus());
        this.setBillDate(bill.getDate());
        this.setMember(bill.getMember());
        this.setDeleteFlag(bill.getDeleteFlag());
        for (BillDetail billDetail : bill.getBillDetails()) {
            DisplayItem displayItem = new DisplayItem();
            displayItem.setId(billDetail.getItem().getId());
            displayItem.setValue(billDetail.getItem().getName());
            displayItem.setDescription(billDetail.getItem().getDescription());
            displayItem.setPrice(billDetail.getItem().getPrice());
            displayItem.setQty(billDetail.getQuantity());
            displayItem.setTotal(billDetail.getItem().getPrice() * billDetail.getQuantity());
            this.getDisplayItemList().add(displayItem);
            totalAmount += displayItem.getTotal();
        }

        double taxAmount = 0;
        for (BillTaxDetail billTaxDetail : bill.getBillTaxDetails()) {
            taxAmount += billTaxDetail.getTaxAmount();
        }

        totalAmount += taxAmount;
        totalAmount =  roundTo2Decimals(totalAmount);
        this.setTotalAmount(totalAmount); //before applying rounding
        this.setRounding(totalAmount);
        if((totalAmount - Math.floor(totalAmount)) >= .5)
            totalAmount = Math.ceil(totalAmount);
        else
            totalAmount = Math.floor(totalAmount);

        this.setGrandTotalAmount(totalAmount);
        this.setRounding(roundTo2Decimals(getGrandTotalAmount() - getRounding()));
        this.setBillTaxDetails(bill.getBillTaxDetails());
        taxListJason = new JsonSerializer().serialize(bill.getBillTaxDetails());
        itemListJason = new JsonSerializer().serialize(displayItemList);
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public List<BillTaxDetail> getBillTaxDetails() {
        return billTaxDetails;
    }

    public void setBillTaxDetails(List<BillTaxDetail> billTaxDetails) {
        this.billTaxDetails = billTaxDetails;
    }

    public String getTaxListJason() {
        return taxListJason;
    }

    public void setTaxListJason(String taxListJason) {
        this.taxListJason = taxListJason;
    }

    public Character getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Character deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public double getGrandTotalAmount() {
        return grandTotalAmount;
    }

    public void setGrandTotalAmount(double grandTotalAmount) {
        this.grandTotalAmount = grandTotalAmount;
    }

    public double getRounding() {
        return rounding;
    }

    public void setRounding(double rounding) {
        this.rounding = rounding;
    }
}
