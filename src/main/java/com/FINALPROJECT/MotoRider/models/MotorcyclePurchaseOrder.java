package com.FINALPROJECT.MotoRider.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MotorcyclePurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String model;

    private BrandType brandType; //marca

    private double price;

    private double shippingCost; //costo envio

    private double totalCost;

    @OneToMany(mappedBy="motorcyclePurchaseOrder", fetch=FetchType.EAGER)
    private Set<Motorcycle> motorcycles = new HashSet<>();

   public MotorcyclePurchaseOrder(){

   }
    public MotorcyclePurchaseOrder(String model, BrandType brandType, double price, double shippingCost, double totalCost, Set<Motorcycle> motorcycles) {
        this.model = model;
        this.brandType = brandType;
        this.price = price;
        this.shippingCost = shippingCost;
        this.totalCost = totalCost;
        this.motorcycles = motorcycles;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Set<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    public void setMotorcycles(Set<Motorcycle> motorcycles) {
        this.motorcycles = motorcycles;
    }
}
