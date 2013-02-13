package com.rcr.domain;

import java.util.ArrayList;
import java.util.List;

public class Person extends Entity {

    private String firstName;

    private String lastName;

    private List<Address> addressList = new ArrayList<Address>();

    private List<Phone> phoneList = new ArrayList<Phone>();

    private String designation;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public Address getAddressDetailsBy(long addressId) {
        if (addressId < 1) return null;
        for (Address address : addressList) {
            if (address.getId() == addressId) return address;
        }
        return null;
    }

    public Phone getPhoneDetailsBy(long phoneId) {
        if (phoneId < 1) return null;
        for (Phone phone : phoneList) {
            if (phone.getId() == phoneId) return phone;
        }
        return null;
    }

    public String getPhoneListAsString() {
        StringBuilder phoneListString = new StringBuilder();
        for (Phone phone : phoneList) {
            phoneListString.append(phone.getDisplayPhone()).append("  ");
        }
        return phoneListString.toString();
    }
}
