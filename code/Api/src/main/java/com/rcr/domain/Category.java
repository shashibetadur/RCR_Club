package com.rcr.domain;

public class Category extends Entity {

    private String name;

    private String description;

    public Category(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    class PurchaseOrder {
        public static final long PURCHASE_ORDER = 1L;
    }

}
