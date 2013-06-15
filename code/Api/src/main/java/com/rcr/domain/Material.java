package com.rcr.domain;

public class Material extends Entity {

    private long inventoryId;

    private String name;

    private String unit;

    private Character materialType;

    private double price;

    private int qty;

    private int total;

    private String description;

    private String value;

    private Long stock;

    public Material() {
        value = name;
    }

    public Material(long id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Character getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Character materialType) {
        this.materialType = materialType;
    }

    public String getMaterialTypeDescription() {
        return MaterialType.getNameByCode(this.materialType);
    }

    public String getValue() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

}
