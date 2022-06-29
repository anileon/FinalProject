package com.FINALPROJECT.MotoRider.dto;

import com.FINALPROJECT.MotoRider.models.BrandType;
import com.FINALPROJECT.MotoRider.models.Motorcycle;

import java.util.List;


public class MotorcycleDTO {

    private Long id;

    private String model;

    private BrandType brandType; //marca

    private String displacement; //cilindrada

    private List<String> images;

    private double price;

    private int stock;

    private boolean isActive;


    public MotorcycleDTO() {
    }

    public MotorcycleDTO(Motorcycle motorcycle) {
        this.id = motorcycle.getId();
        this.model = motorcycle.getModel();
        this.brandType = motorcycle.getBrandType();
        this.displacement = motorcycle.getDisplacement();
        this.images = motorcycle.getImages();
        this.price = motorcycle.getPrice();
        this.stock = motorcycle.getStock();
        this.isActive = motorcycle.isActive();
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public BrandType getBrandType() {
        return brandType;
    }

    public String getDisplacement() {
        return displacement;
    }

    public List<String> getImages() {
        return images;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
