package com.FINALPROJECT.MotoRider.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "receipt",  fetch=FetchType.EAGER)
    private Set<ProductPurchaseOrder> productPurchases;

    @OneToMany(mappedBy = "receipt",  fetch=FetchType.EAGER)
    private Set<MotorcyclePurchaseOrder> motorcyclePurchaseOrders;


    private double shipCost;

    private double totalCost;

    public Receipt() {
    }

    public Receipt(Set<ProductPurchaseOrder> productPurchases, Set<MotorcyclePurchaseOrder> motorcyclePurchaseOrders, double shipCost, double totalCost) {
        this.productPurchases = productPurchases;
        this.motorcyclePurchaseOrders = motorcyclePurchaseOrders;
        this.shipCost = shipCost;
        this.totalCost = totalCost;
    }


    public long getId() {
        return id;
    }


    public Set<ProductPurchaseOrder> getProductPurchases() {
        return productPurchases;
    }

    public void setProductPurchases(Set<ProductPurchaseOrder> productPurchases) {
        this.productPurchases = productPurchases;
    }

    public Set<MotorcyclePurchaseOrder> getMotorcyclePurchaseOrders() {
        return motorcyclePurchaseOrders;
    }

    public void setMotorcyclePurchaseOrders(Set<MotorcyclePurchaseOrder> motorcyclePurchaseOrders) {
        this.motorcyclePurchaseOrders = motorcyclePurchaseOrders;
    }

    public double getShipCost() {
        return shipCost;
    }

    public void setShipCost(double shipCost) {
        this.shipCost = shipCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
