package com.FINALPROJECT.MotoRider.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class ProductPurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy="productPurchaseOrder_id", fetch=FetchType.EAGER)
    private Set<Product> products_id = new HashSet<>();

    private double shipPrice;

    private double totalCost;


    public ProductPurchaseOrder() {
    }

    public ProductPurchaseOrder(double shipPrice) {

        List<Double> precios = new ArrayList<>();
        products_id.stream().forEach(product -> precios.add(product.getPrice()));

        this.shipPrice = shipPrice;

        this.totalCost = precios.stream().reduce(0.0 , (aDouble, aDouble2) -> aDouble + aDouble2);

    }

    public Set<Product> getProducts() {return products_id;}
    public void setProducts(Set<Product> products) {this.products_id = products;}


    public double getShipPrice() {return shipPrice;}
    public void setShipPrice(double shipPrice) {this.shipPrice = shipPrice;}

    public double getTotalCost() {return totalCost;}
    public void setTotalCost(double totalCost) {this.totalCost = totalCost;}

    public long getId() {
        return id;
    }

    public void addProduct(Product product){
        product.setProductPurchaseId(this);
        products_id.add(product);
    }

    public Set<Product> getProducts_id() {
        return products_id;
    }
}
