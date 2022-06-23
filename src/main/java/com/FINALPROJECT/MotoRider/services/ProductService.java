package com.FINALPROJECT.MotoRider.services;

import com.FINALPROJECT.MotoRider.dto.ProductDTO;

import java.util.Set;

public interface ProductService {



    Set<ProductDTO> getProductsDTO();
    ProductDTO getProductById(long id);


}
