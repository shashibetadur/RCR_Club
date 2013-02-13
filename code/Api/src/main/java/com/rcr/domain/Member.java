package com.rcr.domain;

import java.util.ArrayList;
import java.util.List;

public class Member extends Entity {

    private Person personalDetails;

    private String membershipType;

    private String status;

    private List<MembershipDetail> membershipDetails = new ArrayList<MembershipDetail>();

    private List<MemberVisit> memberVisits = new ArrayList<MemberVisit>();

    public Person getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(Person personalDetails) {
        this.personalDetails = personalDetails;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MembershipDetail> getMembershipDetails() {
        return membershipDetails;
    }

    public void setMembershipDetails(List<MembershipDetail> membershipDetails) {
        this.membershipDetails = membershipDetails;
    }

    public List<MemberVisit> getMemberVisits() {
        return memberVisits;
    }

    public void setMemberVisits(List<MemberVisit> memberVisits) {
        this.memberVisits = memberVisits;
    }
}
