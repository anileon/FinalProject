package com.FINALPROJECT.MotoRider.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="productPurchaseId")
    private ProductPurchaseOrder productPurchaseId;

    private String type;

    private GenderType genderType;

    private String description;

    private double price;

    private String productImg;

    private int stock;


    public Product() {
    }

    public Product(ProductPurchaseOrder productPurchaseId, String type, GenderType genderType, String description, double price, String productImg, int stock) {
        this.productPurchaseId = productPurchaseId;
        this.type = type;
        this.genderType = genderType;
        this.description = description;
        this.price = price;
        this.productImg = productImg;
        this.stock = stock;
    }


    public long getId() {return id;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public GenderType getGenderType() {return genderType;}
    public void setGenderType(GenderType genderType) {this.genderType = genderType;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;  }

    public String getProductImg() { return productImg;}
    public void setProductImg(String productImg) {this.productImg = productImg;}

    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}

    public ProductPurchaseOrder getProductPurchaseId() {
        return productPurchaseId;
    }

    public void setProductPurchaseId(ProductPurchaseOrder productPurchaseId) {
        this.productPurchaseId = productPurchaseId;
    }
}
