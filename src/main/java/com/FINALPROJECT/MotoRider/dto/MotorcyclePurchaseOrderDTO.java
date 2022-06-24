package com.FINALPROJECT.MotoRider.dto;

import com.FINALPROJECT.MotoRider.models.MotorcyclePurchaseOrder;

import java.time.LocalDateTime;

public class MotorcyclePurchaseOrderDTO {

    private Long id;

    private int numOfProducts;

    private LocalDateTime datePurchase;


    public MotorcyclePurchaseOrderDTO(){

    }

    public MotorcyclePurchaseOrderDTO(MotorcyclePurchaseOrder motorcyclePurchaseOrder) {
        this.id = motorcyclePurchaseOrder.getId();
        this.numOfProducts = motorcyclePurchaseOrder.getNumOfProducts();
        this.datePurchase = motorcyclePurchaseOrder.getDatePurchase();
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
}
