package com.FINALPROJECT.MotoRider.dto;


import com.FINALPROJECT.MotoRider.models.GenderType;
import com.FINALPROJECT.MotoRider.models.ProductPurchaseOrder;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductPurchaseOrderDTO {

    private long id;
    private String type;
    private GenderType genderType;
    private int size;
    private double shippingPrice;
    private double totalCost;

    private Set<ProductDTO> products;

    public ProductPurchaseOrderDTO() {
    }

    public ProductPurchaseOrderDTO(ProductPurchaseOrder productPurchaseOrder){
        this.id = productPurchaseOrder.getId();
        this.type = productPurchaseOrder.getType();
        this.size = productPurchaseOrder.getSize();
        this.shippingPrice = productPurchaseOrder.getShippingPrice();
        this.products = productPurchaseOrder.getProducts().stream().map(product -> new ProductDTO(product)).collect(Collectors.toSet());
        this.totalCost = productPurchaseOrder.getTotalCost();

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}
