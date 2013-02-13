package com.rcr.domain;


public class MembershipType extends Entity {
    private String name;

    private Character validity;

    private double enrollmentFees;

    private double fees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getValidity() {
        return validity;
    }

    public void setValidity(Character validity) {
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
