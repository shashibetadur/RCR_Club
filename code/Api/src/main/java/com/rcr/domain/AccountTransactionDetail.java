package com.rcr.domain;

public class AccountTransactionDetail extends Entity {


    private CategoryAttribute categoryAttributeId;
    private long accountTransactionId;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getAccountTransactionId() {
        return accountTransactionId;
    }

    public void setAccountTransactionId(long accountTransactionId) {
        this.accountTransactionId = accountTransactionId;
    }

    public CategoryAttribute getCategoryAttributeId() {
        return categoryAttributeId;
    }

    public void setCategoryAttributeId(CategoryAttribute categoryAttributeId) {
        this.categoryAttributeId = categoryAttributeId;
    }
}
