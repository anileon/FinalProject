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

    @OneToMany(mappedBy="productPurchase", fetch=FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    private String Type;

    private GenderType genderType;

    private int size;

    private double shippingPrice;

    private double totalCost;
    private int numbOfProducts;


    public ProductPurchaseOrder() {
    }


    public ProductPurchaseOrder(String type, GenderType genderType, int size, double shippingPrice, double totalCost, int numbOfProducts) {
        Type = type;
        this.genderType = genderType;
        this.size = size;
        this.shippingPrice = shippingPrice;
        this.totalCost = totalCost;
        this.numbOfProducts = numbOfProducts;
    }


    public long getId() {
        return id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public int getNumbOfProducts() {
        return numbOfProducts;
    }

    public void setNumbOfProducts(int numbOfProducts) {
        this.numbOfProducts = numbOfProducts;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void addProduct(Product product){
        product.setProductPurchase(this);
        products.add(product);
    }


}
