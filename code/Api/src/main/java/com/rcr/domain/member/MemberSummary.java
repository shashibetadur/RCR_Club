package com.rcr.domain.member;

import com.rcr.domain.account.MembershipPayment;

import java.util.Date;
import java.util.List;

public class MemberSummary {

    private String membershipType;

    private String status;

    private Date expiryDate;

    private double pendingAmount;

    public MemberSummary(Member memberDetails, MembershipDetails membershipDetails, List<MembershipPayment> membershipPaymentDetails) {
        MembershipDetail currentMembership = membershipDetails.getCurrentMembership();
        status = memberDetails.getStatus();
        if (currentMembership != null) {
            membershipType = currentMembership.getMembershipType().getName();
            expiryDate = currentMembership.getEndDate();
        } else {
            membershipType = "NONE";
        }
        double amount = membershipDetails.getMembershipAmount();
        double membershipAmountPaid = 0;
        for (MembershipPayment membershipPayment : membershipPaymentDetails) {
            membershipAmountPaid += membershipPayment.getAmount();
        }
        pendingAmount = amount - membershipAmountPaid;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public String getStatus() {
        return status;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public double getPendingAmount() {
        return pendingAmount;
    }
}
