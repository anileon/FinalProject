package com.FINALPROJECT.MotoRider.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;


    private double shipCost;

    private double totalCost;

    private LocalDateTime localDateTime;

    public Receipt() {
    }

    public Receipt(Client client, LocalDateTime localDateTime){
        this.client = client;
        this.localDateTime = localDateTime;
    }

    public Receipt(Client client, Set<ProductPurchaseOrder> productPurchases, double shipCost, double totalCost, LocalDateTime localDateTime){
        this.client = client;
        this.productPurchases = productPurchases;
        this.shipCost = shipCost;
        this.totalCost = totalCost;
        this.localDateTime = localDateTime;

    }

    public Receipt( Client client, Set<MotorcyclePurchaseOrder> motorcyclePurchaseOrders, LocalDateTime localDateTime ,double shipCost, double totalCost){
        this.client = client;
        this.motorcyclePurchaseOrders = motorcyclePurchaseOrders;
        this.shipCost = shipCost;
        this.totalCost = totalCost;
        this.localDateTime = localDateTime;

    }

    public Receipt(Set<ProductPurchaseOrder> productPurchases, Set<MotorcyclePurchaseOrder> motorcyclePurchaseOrders, double shipCost, double totalCost, Client client, LocalDateTime localDateTime) {
        this.productPurchases = productPurchases;
        this.motorcyclePurchaseOrders = motorcyclePurchaseOrders;
        this.shipCost = shipCost;
        this.totalCost = totalCost;
        this.client = client;
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addMoto(MotorcyclePurchaseOrder motorcyclePurchaseOrder){
        motorcyclePurchaseOrder.setReceipt(this);
        motorcyclePurchaseOrders.add(motorcyclePurchaseOrder);
    }

    public void addProduct(ProductPurchaseOrder productPurchaseOrder){
        productPurchaseOrder.setReceipt(this);
        productPurchases.add(productPurchaseOrder);
    }

    public void addProductMoto(MotorcyclePurchaseOrder motorcyclePurchaseOrder, ProductPurchaseOrder productPurchaseOrder){
        motorcyclePurchaseOrder.setReceipt(this);
        productPurchaseOrder.setReceipt(this);
        motorcyclePurchaseOrders.add(motorcyclePurchaseOrder);
        productPurchases.add(productPurchaseOrder);
    }


}
