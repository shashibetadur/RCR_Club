package com.rcr.domain;

public class Phone extends Entity {

    public enum Type {MOBILE, HOME, OFFICE, OTHER}

    private long personId;

    private String phoneType;

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getDisplayPhone() {
        return phoneType + ":" + phoneNumber;
    }
}
