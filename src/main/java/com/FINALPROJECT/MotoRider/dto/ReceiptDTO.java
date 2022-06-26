package com.FINALPROJECT.MotoRider.dto;


import com.FINALPROJECT.MotoRider.models.Receipt;

import java.util.Set;
import java.util.stream.Collectors;

public class ReceiptDTO {


    private long id;
    private Set<MotorcyclePurchaseOrderDTO> motorcyclePurchaseOrderDTOS;
    private Set<ProductPurchaseOrderDTO> productPurchaseOrderDTOS;
    private double shippingPrice;
    private double totalCost;

    public ReceiptDTO() {
    }


    public ReceiptDTO(Receipt receipt){

        this.id = receipt.getId();
        this.motorcyclePurchaseOrderDTOS = receipt.getMotorcyclePurchaseOrders().stream()
                .map(motorcyclePurchaseOrder -> new MotorcyclePurchaseOrderDTO(motorcyclePurchaseOrder)).collect(Collectors.toSet());
        this.productPurchaseOrderDTOS = receipt.getProductPurchases().stream()
                .map(productPurchaseOrder -> new ProductPurchaseOrderDTO(productPurchaseOrder)).collect(Collectors.toSet());
        this.shippingPrice = receipt.getShipCost();
        this.totalCost = receipt.getTotalCost();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<MotorcyclePurchaseOrderDTO> getMotorcyclePurchaseOrderDTOS() {
        return motorcyclePurchaseOrderDTOS;
    }

    public void setMotorcyclePurchaseOrderDTOS(Set<MotorcyclePurchaseOrderDTO> motorcyclePurchaseOrderDTOS) {
        this.motorcyclePurchaseOrderDTOS = motorcyclePurchaseOrderDTOS;
    }

    public Set<ProductPurchaseOrderDTO> getProductPurchaseOrderDTOS() {
        return productPurchaseOrderDTOS;
    }

    public void setProductPurchaseOrderDTOS(Set<ProductPurchaseOrderDTO> productPurchaseOrderDTOS) {
        this.productPurchaseOrderDTOS = productPurchaseOrderDTOS;
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
}
