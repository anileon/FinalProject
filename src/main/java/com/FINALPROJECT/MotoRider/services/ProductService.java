package com.FINALPROJECT.MotoRider.services;

import com.FINALPROJECT.MotoRider.dto.ProductDTO;
import com.FINALPROJECT.MotoRider.models.Product;

import java.util.Set;

public interface ProductService {


    void saveProduct(Product product);
    Set<ProductDTO> getProductsDTO();
    ProductDTO getProductById(long id);

    Product getProduct(long id);

}
