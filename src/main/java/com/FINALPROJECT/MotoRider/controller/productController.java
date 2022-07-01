package com.FINALPROJECT.MotoRider.controller;


import com.FINALPROJECT.MotoRider.dto.ProductDTO;
import com.FINALPROJECT.MotoRider.dto.ProductToAdd;
import com.FINALPROJECT.MotoRider.models.Client;
import com.FINALPROJECT.MotoRider.models.Motorcycle;
import com.FINALPROJECT.MotoRider.models.Product;
import com.FINALPROJECT.MotoRider.services.ClientService;
import com.FINALPROJECT.MotoRider.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class productController {


    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;
    @GetMapping("/products")
    public Set<ProductDTO> getAll(){
        return productService.getProductsDTO().stream().filter(productDTO -> productDTO.isActive() == true).collect(Collectors.toSet());
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProductDTO(@PathVariable long id){
        return productService.getProductById(id);
    }

    @PatchMapping("/admin/product")
    public ResponseEntity<Object> addStockProduct(Authentication authentication, @RequestParam long id, @RequestParam int stockAgregar){

        Client admin = clientService.getCurrent(authentication);



        Product product = productService.getProduct(id);

        if(!admin.getEmail().contains("@admin")){
            return new ResponseEntity<>("Admin only endpoint", HttpStatus.FORBIDDEN);
        }
        if ( id == 0){
            return new ResponseEntity<>("Invalid id", HttpStatus.FORBIDDEN);
        }
        if(stockAgregar <= 0){
            return new ResponseEntity<>("Invalid stock amount", HttpStatus.FORBIDDEN);
        }
        if(product == null){
            return new ResponseEntity<>("Product does not exist", HttpStatus.FORBIDDEN);
        }



        product.setStock(product.getStock() + stockAgregar);
        productService.saveProduct(product);

        return new ResponseEntity<>("Stock Updated", HttpStatus.ACCEPTED);
    };

    @PatchMapping("/admin/eliminarProduct")
    public ResponseEntity<Object> deleteProduct(Authentication authentication, @RequestParam long id){
        Client admin = clientService.getCurrent(authentication);
        Product product = productService.getProduct(id);

        if(!admin.getEmail().contains("@admin")){
            new ResponseEntity<>("Only admin fution", HttpStatus.FORBIDDEN);
        }
        if(product != null){
            new ResponseEntity<>("Invalid Motorcycle", HttpStatus.FORBIDDEN);
        }

        product.setActive(false);
        productService.saveProduct(product);

        return new ResponseEntity<>("Product Deleted", HttpStatus.ACCEPTED);

    }


    @PostMapping("/admin/product")
    public ResponseEntity<Object> newProduct(@RequestBody ProductToAdd productToAdd){

        // cliente admin

       if(productToAdd == null){
           return new ResponseEntity<>("invalid product", HttpStatus.FORBIDDEN);
       }
       if(productToAdd.getStock() <= 0){
           return new ResponseEntity<>("Invalid product Stock", HttpStatus.FORBIDDEN);
       }
       if(productToAdd.getDescription().isEmpty()){
           return new ResponseEntity<>("Description is empty", HttpStatus.FORBIDDEN);
       }

       Product product = new Product(productToAdd.getType(), productToAdd.getGenderType(), productToAdd.getDescription(),
               productToAdd.getPrice(), productToAdd.getProductImg(), productToAdd.getStock(), productToAdd.getSize());
       productService.saveProduct(product);

       return new ResponseEntity<>("Created", HttpStatus.ACCEPTED);
    }



}
