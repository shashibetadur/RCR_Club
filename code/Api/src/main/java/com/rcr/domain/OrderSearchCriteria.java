package com.rcr.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isEmpty;

public class OrderSearchCriteria {
    private String orderId;
    private String phone;
    private String firstName;
    private String lastName;
    private Date fromDate;
    private Date toDate;
    private List<Character> orderStatusList = new ArrayList<Character>();

    public OrderSearchCriteria() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public List<Character> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(List<Character> orderStatusList) {
        this.orderStatusList = orderStatusList;
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

    public boolean hasOrderId() {
        try {
            return Long.parseLong(orderId) > 0;
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
}
