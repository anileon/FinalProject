package com.FINALPROJECT.MotoRider.controller;


import com.FINALPROJECT.MotoRider.dto.MotorcycleDTO;
import com.FINALPROJECT.MotoRider.dto.ReceiptDTO;
import com.FINALPROJECT.MotoRider.dto.RecipeToCreateDTO;
import com.FINALPROJECT.MotoRider.models.*;
import com.FINALPROJECT.MotoRider.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReceiptController {

    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;
    @Autowired
    MotorcycleService motorcycleService;
    @Autowired
    ProductPurchaseOrderService productPurchaseOrderService;
    @Autowired
    MotorcyclePurchaseOrderService motorcyclePurchaseOrderService;
    @Autowired
    ReceiptService receiptService;


    @GetMapping("/receipt/{id}")
    public ReceiptDTO getReceiptDto(@PathVariable long id){

        return receiptService.getReceiptDto(id);
    }

    @Transactional

     @PostMapping("/comprar")
    public ResponseEntity<Object> Purchase(@RequestBody RecipeToCreateDTO recipeToCreateDTO){

        Client cliente1 = clientService.getClient(1);

        Receipt receipt = new Receipt(cliente1, LocalDateTime.now());
        receiptService.saveReceipt(receipt);

        Set<Product> products = recipeToCreateDTO.getProducts().stream().map(productToPurchaseDTO -> productService.getProduct(productToPurchaseDTO.getIdProducto())).collect(Collectors.toSet());
        Set<Motorcycle> motorcycles = recipeToCreateDTO.getMotors().stream().map(motoToPurchaseDTO -> motorcycleService.getMoto(motoToPurchaseDTO.getId())).collect(Collectors.toSet());

        Set<Double> subTotalMotos = new HashSet<>();
        if (motorcycles.size()>0){
            motorcycles.forEach(motorcycle -> {

                int cantidadMotoComprar = recipeToCreateDTO.getMotors().stream().filter(moto -> moto.getId() == motorcycle.getId()).findFirst().orElseThrow().getCantidad();


                motorcycle.setStock(motorcycle.getStock()-cantidadMotoComprar);
                motorcycleService.saveMotorcycle(motorcycle);
                MotorcyclePurchaseOrder motorcyclePurchaseOrder = new MotorcyclePurchaseOrder(cantidadMotoComprar, LocalDateTime.now(),motorcycle, receipt);
                subTotalMotos.add(motorcyclePurchaseOrder.getCost());
                motorcyclePurchaseOrderService.saveMotorcyclePurchaseOrder(motorcyclePurchaseOrder);

            });
        }

         Set<Double> subtotalProductos = new HashSet<>();
         if(products.size()>0){
             products.forEach(product -> {
                 int cantidadComprar = recipeToCreateDTO.getProducts().stream().filter(prod -> prod.getIdProducto() == product.getId()).findFirst().orElseThrow().getCantidad();

                 product.setStock(product.getStock()-cantidadComprar);
                 productService.saveProduct(product);
                 ProductPurchaseOrder productPurchaseOrder = new ProductPurchaseOrder(product, LocalDateTime.now(), cantidadComprar, receipt);
                 subtotalProductos.add(productPurchaseOrder.getCost());
                 productPurchaseOrderService.saveProductPurchaseOrder(productPurchaseOrder);

             });
         }

        Double totalProducts = subtotalProductos.stream().reduce(Double::sum).orElse(0.0);
        Double totalMotos = subTotalMotos.stream().reduce(Double::sum).orElse(0.0);;
        receipt.setTotalCost(totalProducts + totalMotos);

        receiptService.saveReceipt(receipt);
         return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}
