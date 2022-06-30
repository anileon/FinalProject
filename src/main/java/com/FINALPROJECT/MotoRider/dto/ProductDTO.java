package com.FINALPROJECT.MotoRider.dto;


import com.FINALPROJECT.MotoRider.models.GenderType;
import com.FINALPROJECT.MotoRider.models.Product;

import java.util.List;

public class ProductDTO {

    private long id;

    private int size;

    private String type;
    private List<String> urlImg;

    private GenderType genderType;

    private String description;

    private double price;


    private int stock;
    private boolean isActive;

    public ProductDTO() {
    }

    public ProductDTO(Product product){

        this.id = product.getId();
        this.type = product.getType();
        this.genderType = product.getGenderType();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.size = product.getSize();
        this.urlImg = product.getProductImg();
        this.isActive = product.isActive();
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public GenderType getGenderType() {return genderType;}
    public void setGenderType(GenderType genderType) {this.genderType = genderType;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(List<String> urlImg) {
        this.urlImg = urlImg;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
