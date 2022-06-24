package com.FINALPROJECT.MotoRider.dto;


import com.FINALPROJECT.MotoRider.models.GenderType;
import com.FINALPROJECT.MotoRider.models.Product;
import com.FINALPROJECT.MotoRider.models.ProductPurchaseOrder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductPurchaseOrderDTO {

    private long id;
    private ProductDTO product;
    private int numbOfPurchase;
    private LocalDateTime timeOfPurchase;


    public ProductPurchaseOrderDTO() {
    }

    public ProductPurchaseOrderDTO(ProductPurchaseOrder productPurchaseOrder){

        this.id = productPurchaseOrder.getId();
        this.product = new ProductDTO(productPurchaseOrder.getProducts());
        this.numbOfPurchase = productPurchaseOrder.getNumOfProducts();
        this.timeOfPurchase = productPurchaseOrder.getTimeOfPurchase();

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getNumbOfPurchase() {
        return numbOfPurchase;
    }

    public void setNumbOfPurchase(int numbOfPurchase) {
        this.numbOfPurchase = numbOfPurchase;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }
}
