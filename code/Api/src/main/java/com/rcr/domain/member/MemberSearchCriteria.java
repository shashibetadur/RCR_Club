package com.rcr.domain.member;

import static org.apache.commons.lang.StringUtils.isEmpty;

public class MemberSearchCriteria {
    private String memberId;
    private String phone;
    private String firstName;
    private String lastName;

    public MemberSearchCriteria() {
    }

    public MemberSearchCriteria(String phone, String firstName, String lastName) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public boolean hasPhoneAndNameCriteria() {
        return has(phone) && (has(firstName) || has(lastName));
    }

    public boolean hasFirstAndLasNameCriteria() {
        return has(firstName) && has(lastName);
    }

    public boolean hasPhoneCriteria() {
        return has(phone);
    }

    public boolean hasNameCriteria() {
        return has(firstName) || has(lastName);
    }

    private boolean has(String value) {
        return !isEmpty(value);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public boolean hasMemberId() {
        try {
            return Long.parseLong(memberId) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
