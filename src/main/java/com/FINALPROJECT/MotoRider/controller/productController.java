package com.FINALPROJECT.MotoRider.controller;


import com.FINALPROJECT.MotoRider.dto.ProductDTO;
import com.FINALPROJECT.MotoRider.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class productController {


    @Autowired
    ProductService productService;
    @GetMapping("/products")
    public Set<ProductDTO> getAll(){
        return productService.getProductsDTO();
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProductDTO(@PathVariable long id){
        return productService.getProductById(id);
    }

}
