package com.FINALPROJECT.MotoRider.dto;

import com.FINALPROJECT.MotoRider.models.MotorcyclePurchaseOrder;

import java.time.LocalDateTime;

public class MotorcyclePurchaseOrderDTO {

    private Long id;
    private MotorcycleDTO motorcycle;


    private int numOfProducts;

    private LocalDateTime datePurchase;

    private double cost;


    public MotorcyclePurchaseOrderDTO(){

    }

    public MotorcyclePurchaseOrderDTO(MotorcyclePurchaseOrder motorcyclePurchaseOrder) {
        this.id = motorcyclePurchaseOrder.getId();
        this.motorcycle = new MotorcycleDTO(motorcyclePurchaseOrder.getMotorcycle());
        this.numOfProducts = motorcyclePurchaseOrder.getNumOfProducts();
        this.datePurchase = motorcyclePurchaseOrder.getDatePurchase();
        this.cost = motorcyclePurchaseOrder.getCost();
    }

    public Long getId() {
        return id;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public LocalDateTime getDatePurchase() {
        return datePurchase;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNumOfProducts(int numOfProducts) {
        this.numOfProducts = numOfProducts;
    }

    public void setDatePurchase(LocalDateTime datePurchase) {
        this.datePurchase = datePurchase;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public MotorcycleDTO getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(MotorcycleDTO motorcycle) {
        this.motorcycle = motorcycle;
    }
}
