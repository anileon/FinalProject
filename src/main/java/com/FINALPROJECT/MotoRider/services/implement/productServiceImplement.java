package com.FINALPROJECT.MotoRider.services.implement;


import com.FINALPROJECT.MotoRider.dto.ProductDTO;
import com.FINALPROJECT.MotoRider.repositories.ProductRepository;
import com.FINALPROJECT.MotoRider.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class productServiceImplement implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public Set<ProductDTO> getProductsDTO() {
        return productRepository.findAll().stream().map(product -> new ProductDTO(product)).collect(Collectors.toSet());
    }

    @Override
    public ProductDTO getProductById(long id) {
        return productRepository.findById(id).map(product -> new ProductDTO(product)).orElse(null);
    }
}
