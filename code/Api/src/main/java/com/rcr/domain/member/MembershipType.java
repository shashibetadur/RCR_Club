package com.rcr.domain.member;


import com.rcr.domain.Entity;

public class MembershipType extends Entity {
    private String name;

    private long validity;

    private double enrollmentFees;

    private double fees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValidity() {
        return validity;
    }

    public void setValidity(long validity) {
        this.validity = validity;
    }

    public double getEnrollmentFees() {
        return enrollmentFees;
    }

    public void setEnrollmentFees(double enrollmentFees) {
        this.enrollmentFees = enrollmentFees;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
