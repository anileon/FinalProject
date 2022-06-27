package com.FINALPROJECT.MotoRider.controller;


import com.FINALPROJECT.MotoRider.dto.ProductDTO;
import com.FINALPROJECT.MotoRider.dto.ProductToAdd;
import com.FINALPROJECT.MotoRider.models.Motorcycle;
import com.FINALPROJECT.MotoRider.models.Product;
import com.FINALPROJECT.MotoRider.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PatchMapping("/admin/product")
    public ResponseEntity<Object> addStockProduct(@RequestParam long id, @RequestParam int stockAgregar){

        //    Client client (Aca tiene que ser un admin)



        Product product = productService.getProduct(id);

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
