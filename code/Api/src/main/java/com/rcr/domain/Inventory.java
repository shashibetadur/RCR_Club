package com.rcr.domain;

import java.util.Date;

public class Inventory extends Entity {

    private Material material;

    private Date date;

    private Date entryDate;

    private long currentStock;

    private long quantity;

    private Character creditDebit;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(long currentStock) {
        this.currentStock = currentStock;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Character getCreditDebit() {
        return creditDebit;
    }

    public void setCreditDebit(Character creditDebit) {
        this.creditDebit = creditDebit;
    }
}
