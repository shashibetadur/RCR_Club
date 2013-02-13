package com.rcr.domain;

import java.util.Date;

public class MembershipDetail extends Entity {

    private long memberId;
    private MembershipType membershipType;
    private Date startDate;
    private Date endDate;
    private double enrollmentFees;
    private double fees;

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public double getEnrollmentFees() {
        return enrollmentFees;
    }

    public void setEnrollmentFees(double enrollmentFees) {
        this.enrollmentFees = enrollmentFees;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
