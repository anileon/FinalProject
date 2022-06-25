package com.FINALPROJECT.MotoRider.dto;


import java.util.Set;

public class RecipeToCreateDTO {

    private Set<ProductToPurchaseDTO> products;
    private Set<MotoToPurchaseDTO> motors;


    public RecipeToCreateDTO(Set<ProductToPurchaseDTO> products, Set<MotoToPurchaseDTO> motors) {
        this.products = products;
        this.motors = motors;
    }




    public Set<ProductToPurchaseDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductToPurchaseDTO> products) {
        this.products = products;
    }

    public Set<MotoToPurchaseDTO> getMotors() {
        return motors;
    }

    public void setMotors(Set<MotoToPurchaseDTO> motors) {
        this.motors = motors;
    }
}
