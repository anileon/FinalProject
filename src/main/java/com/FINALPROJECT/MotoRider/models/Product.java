package com.FINALPROJECT.MotoRider.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;


    @OneToMany(mappedBy = "products",  fetch=FetchType.EAGER)
    private Set<ProductPurchaseOrder> productPurchases;

    private String type;

    private int size;

    private GenderType genderType;

    private String description;

    private double price;

    @ElementCollection
    @Column(name = "productImages")
    private List<String> productImg;

    private int stock;
    private boolean isActive;


    public Product() {
    }

    public Product(String type, GenderType genderType, String description, double price, List<String> productImg, int stock, int size) {
        this.type = type;
        this.genderType = genderType;
        this.description = description;
        this.price = price;
        this.productImg = productImg;
        this.stock = stock;
        this.size = size;
        this.isActive = true;
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

    public List<String> getProductImg() {
        return productImg;
    }

    public void setProductImg(List<String> productImg) {
        this.productImg = productImg;
    }

    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Set<ProductPurchaseOrder> getProductPurchases() {
        return productPurchases;
    }

    public void setProductPurchases(Set<ProductPurchaseOrder> productPurchases) {
        this.productPurchases = productPurchases;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
