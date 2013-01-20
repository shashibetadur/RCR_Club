package com.rcr.domain;

public class Member extends Entity {

    private Person personalDetails;

    private String membershipType;

    public Member() {
        personalDetails = new Person();
    }

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
}
