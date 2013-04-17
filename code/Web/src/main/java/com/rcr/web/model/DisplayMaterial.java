package com.rcr.web.model;

import com.rcr.domain.Inventory;

public class DisplayMaterial {

    private long id;

    private String name;

    private String unit;

    private Character materialType;

    private double price;

    private long qty;

    private double total;

    private String description;

    private String value;

    private Inventory inventory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal() {
        return qty * price;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Character getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Character materialType) {
        this.materialType = materialType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
