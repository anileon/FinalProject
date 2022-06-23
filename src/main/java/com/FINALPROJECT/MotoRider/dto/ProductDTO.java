package com.FINALPROJECT.MotoRider.dto;


import com.FINALPROJECT.MotoRider.models.GenderType;
import com.FINALPROJECT.MotoRider.models.Product;

public class ProductDTO {

    private long id;

    private String type;

    private GenderType genderType;

    private String description;

    private double price;

    private String productImg;

    private int stock;

    public ProductDTO() {
    }

    public ProductDTO(Product product){

        this.id = product.getId();
        this.type = product.getType();
        this.genderType = product.getGenderType();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.productImg = product.getProductImg();
        this.stock = product.getStock();
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

    public String getProductImg() {return productImg;}
    public void setProductImg(String productImg) {this.productImg = productImg;}

    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}
}
