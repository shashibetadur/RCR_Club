package com.rcr.domain;

public class Item extends Entity {

    private long materialId;
    private String name;
    private Character type;
    private double price;
    private String description;
    private String value;
    private int qty;
    private int total;

    public Item() {
    }

    public Item(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemTypeDescription() {
        return ItemType.getNameByCode(this.type);
    }

    public String getValue() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
