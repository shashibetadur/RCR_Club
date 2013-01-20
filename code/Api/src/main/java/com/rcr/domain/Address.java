package com.rcr.domain;

import static org.apache.commons.lang.StringUtils.isEmpty;

public class Address extends Entity {

    public enum Type{ HOME,OFFICE,OTHER}

    private long personId;

    private String addressLineOne;

    private String addressLineTwo;

    private String locality;

	private String city;

    private String pinCode;

	private String state;

	private String landmark;

	private String addressType;

	private String notes;

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getDisplayAddress(){
        StringBuffer addressString = new StringBuffer(addressType).append(":");
        if(!isEmpty(addressLineOne)) addressString.append(addressLineOne).append(", ");
        if(!isEmpty(addressLineTwo)) addressString.append(addressLineTwo).append(", ");
        if(!isEmpty(locality)) addressString.append(locality).append(", ");
        if(!isEmpty(pinCode)) addressString.append(pinCode).append(", ");
        if(!isEmpty(city)) addressString.append(city).append(", ");
        if(!isEmpty(state)) addressString.append(state).append(", ");
        if(!isEmpty(landmark)) addressString.append(state);
        return addressString.toString();
    }
}
