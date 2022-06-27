package com.FINALPROJECT.MotoRider.dto;

import com.FINALPROJECT.MotoRider.models.GenderType;

import java.util.List;

public class ProductToAdd {

    private String type;

    private int size;

    private GenderType genderType;

    private String description;

    private double price;

    private List<String> productImg;

    private int stock;


    public ProductToAdd() {
    }

    public ProductToAdd(String type, int size, GenderType genderType, String description, double price, List<String> productImg, int stock) {
        this.type = type;
        this.size = size;
        this.genderType = genderType;
        this.description = description;
        this.price = price;
        this.productImg = productImg;
        this.stock = stock;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getProductImg() {
        return productImg;
    }

    public void setProductImg(List<String> productImg) {
        this.productImg = productImg;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
