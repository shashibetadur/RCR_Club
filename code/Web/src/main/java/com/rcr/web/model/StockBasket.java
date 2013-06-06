package com.rcr.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockBasket {

    private List<DisplayMaterial> displayMaterialList = new ArrayList<DisplayMaterial>();

    private String materialListJason="[]";

    private long materialId;

    private Date onDate;

    public StockBasket(){

    }

    public List<DisplayMaterial> getDisplayMaterialList() {
        return displayMaterialList;
    }

    public void setDisplayMaterialList(List<DisplayMaterial> displayMaterialList) {
        this.displayMaterialList = displayMaterialList;
    }

    public String getMaterialListJason() {
        return materialListJason;
    }

    public void setMaterialListJason(String materialListJason) {
        this.materialListJason = materialListJason;
    }

    public long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }
}
