package com.rcr.domain;

import java.util.ArrayList;
import java.util.List;

public class Member extends Entity {

    private Person personalDetails = new Person();

    private String membershipType;

    private String status;

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
}
