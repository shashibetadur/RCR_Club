package com.rcr.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isEmpty;

public class BillSearchCriteria {
    private String billId;
    private String searchMemberId;
    private String phone;
    private String firstName;
    private String lastName;
    private Date fromDate;
    private Date toDate;
    private Long memberId;
    private List<String> billStatusList = new ArrayList<String>();

    public BillSearchCriteria() {
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getBillStatusList() {
        return billStatusList;
    }

    public void setBillStatusList(List<String> billStatusList) {
        this.billStatusList = billStatusList;
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

    public boolean hasBillId() {
        try {
            return Long.parseLong(billId) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean hasSearchMemberId() {
        try {
            return Long.parseLong(searchMemberId) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean has(String value) {
        return !isEmpty(value);
    }

    public boolean hasDateRange() {
        return fromDate != null && toDate != null;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public boolean hasMemberId() {
        return memberId != null;
    }

    public void setSearchMemberId(String searchMemberId) {
        this.searchMemberId = searchMemberId;
    }

    public String getSearchMemberId() {
        return searchMemberId;
    }
}
