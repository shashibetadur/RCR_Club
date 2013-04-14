package com.rcr.domain;

public class PurchaseOrderDetail extends Entity {

    private long purchaseOrderId;

    private Material material;

    private long quantity;

    private long inventoryId;

    private Inventory inventory;

    public long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
