package com.FINALPROJECT.MotoRider.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Motorcycle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String model;

    private BrandType brandType; //marca

    private String displacement; //cilindrada

    @ElementCollection
    @Column(name = "Images")
    private List<String> images;

    private double price;

    private int stock;
    private boolean isActive;


    @OneToMany(mappedBy = "motorcycle", fetch = FetchType.EAGER)
    private Set<MotorcyclePurchaseOrder> motorcyclePurchaseOrders = new HashSet<>();


    public Motorcycle() {
    }

    public Motorcycle(String model, BrandType brandType, String displacement, List<String> images, double price, int stock) {
        this.model = model;
        this.brandType = brandType;
        this.displacement = displacement;
        this.images = images;
        this.price = price;
        this.stock = stock;
        this.isActive = true;
    }

    public Long getId() {
        return id;
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

    public Set<MotorcyclePurchaseOrder> getMotorcyclePurchaseOrders() {
        return motorcyclePurchaseOrders;
    }

    public void setMotorcyclePurchaseOrders(Set<MotorcyclePurchaseOrder> motorcyclePurchaseOrders) {
        this.motorcyclePurchaseOrders = motorcyclePurchaseOrders;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}