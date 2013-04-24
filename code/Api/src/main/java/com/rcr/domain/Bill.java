package com.rcr.domain;

import com.rcr.domain.member.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Bill extends Entity {

    private Member member;

    private Date date;

    private double amount;

    private String status;

    private long guestCount;

    private List<BillDetail> billDetails = new ArrayList<BillDetail>();

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public long getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(long guestCount) {
        this.guestCount = guestCount;
    }

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }
}
