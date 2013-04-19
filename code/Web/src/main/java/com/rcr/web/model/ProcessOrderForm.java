package com.rcr.web.model;

import com.rcr.domain.Material;
import com.rcr.domain.OrderStatus;
import com.rcr.domain.PurchaseOrder;
import com.rcr.domain.PurchaseOrderDetail;
import com.rcr.web.JsonSerializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessOrderForm {

    private long id;

    private Character orderStatus;

    private String orderStatusDescription;

    private List<Material> materialList = new ArrayList<Material>();

    private List<DisplayMaterial> displayMaterialList = new ArrayList<DisplayMaterial>();

    private String materialListJason="[]";

    private double totalAmount;

    private Date orderDate;

    public ProcessOrderForm() {
    }

    public List<DisplayMaterial> getDisplayMaterialList() {
        return displayMaterialList;
    }

    public void setDisplayMaterialList(List<DisplayMaterial> materialDisplayList) {
        this.displayMaterialList = materialDisplayList;
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

    public Character getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Character orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PurchaseOrder buildOrder() {

        double totalAmount = 0;
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(id);
        purchaseOrder.setStatus(orderStatus);
        purchaseOrder.setDate(orderDate);
        for(DisplayMaterial displayMaterial: displayMaterialList ){
            PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
            purchaseOrderDetail.setMaterial(new com.rcr.domain.Material(displayMaterial.getId()));
            purchaseOrderDetail.setQuantity(displayMaterial.getQty());
            purchaseOrder.getPurchaseOrderDetails().add(purchaseOrderDetail);
            totalAmount += (displayMaterial.getPrice() * displayMaterial.getQty());
        }
        purchaseOrder.setAmount(totalAmount);
        return purchaseOrder;
    }

    public void buildDisplayOrder(PurchaseOrder purchaseOrder) {

        double totalAmount = 0;
        this.setId(purchaseOrder.getId());
        this.setOrderStatus(purchaseOrder.getStatus());
        this.setOrderDate(purchaseOrder.getDate());
        for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrder.getPurchaseOrderDetails()) {
            DisplayMaterial displayMaterial = new DisplayMaterial();
            displayMaterial.setId(purchaseOrderDetail.getMaterial().getId());
            displayMaterial.setName(purchaseOrderDetail.getMaterial().getName());
            displayMaterial.setDescription(purchaseOrderDetail.getMaterial().getDescription());
            displayMaterial.setPrice(purchaseOrderDetail.getMaterial().getPrice());
            displayMaterial.setQty(purchaseOrderDetail.getQuantity());
            displayMaterial.setTotal(purchaseOrderDetail.getMaterial().getPrice() * purchaseOrderDetail.getQuantity());
            this.getDisplayMaterialList().add(displayMaterial);
            totalAmount += displayMaterial.getTotal();
        }
        this.setTotalAmount(totalAmount);
        materialListJason = new JsonSerializer().serialize(displayMaterialList);
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderStatusDescription() {
        return OrderStatus.getNameByCode(orderStatus);
    }

    public void setOrderStatusDescription(String orderStatusDescription) {
        this.orderStatusDescription = orderStatusDescription;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
