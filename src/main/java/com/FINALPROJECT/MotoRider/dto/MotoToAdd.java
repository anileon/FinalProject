package com.FINALPROJECT.MotoRider.dto;


import com.FINALPROJECT.MotoRider.models.BrandType;

import java.util.List;

public class MotoToAdd {

    private String model;

    private BrandType brandType; //marca

    private String displacement; //cilindrada

    private List<String> images;

    private double price;

    private int stock;


    public MotoToAdd() {
    }

    public MotoToAdd(String model, BrandType brandType, String displacement, List<String> images, double price, int stock) {
        this.model = model;
        this.brandType = brandType;
        this.displacement = displacement;
        this.images = images;
        this.price = price;
        this.stock = stock;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BrandType getBrandType() {
        return brandType;
    }

    public void setBrandType(BrandType brandType) {
        this.brandType = brandType;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
