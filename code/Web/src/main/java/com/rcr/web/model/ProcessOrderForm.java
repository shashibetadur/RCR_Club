package com.rcr.web.model;

import com.rcr.domain.Material;

import java.util.ArrayList;
import java.util.List;

public class ProcessOrderForm {

    private long id;

    private String orderStatus;

    private List<Material> materialList = new ArrayList<Material>();

    private String materialListJason="[]";

    public ProcessOrderForm() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaterialListJason() {
        return materialListJason;
    }

    public void setMaterialListJason(String materialListJason) {
        this.materialListJason = materialListJason;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
