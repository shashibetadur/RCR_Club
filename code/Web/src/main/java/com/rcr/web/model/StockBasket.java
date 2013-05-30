package com.rcr.web.model;

import java.util.ArrayList;
import java.util.List;

public class StockBasket {

    private List<DisplayMaterial> displayMaterialList = new ArrayList<DisplayMaterial>();

    private String materialListJason="[]";

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
}
