package com.FINALPROJECT.MotoRider.controller;


import com.FINALPROJECT.MotoRider.dto.MotorcycleDTO;
import com.FINALPROJECT.MotoRider.dto.ReceiptDTO;
import com.FINALPROJECT.MotoRider.dto.RecipeToCreateDTO;
import com.FINALPROJECT.MotoRider.models.*;
import com.FINALPROJECT.MotoRider.services.*;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

   /* @PostMapping("/receipt/pdf")
    public ResponseEntity<Object> createPDF(@RequestBody RecipeToCreateDTO recipeToCreateDTO) throws IOException, DocumentException {

        Client cliente1 = clientService.getClient(1);//TRAER EL AUTENTIFICADO

        //INICIO PDF

        Receipt factura = receiptService.getReceipt(1);
        Document document = new Document();

        String ruta = System.getProperty("user.home");
        PdfWriter.getInstance(document,new FileOutputStream(ruta+ File.separator+ "Desktop/factura.pdf"));

        document.open();

        Paragraph paragraph1 = new Paragraph();
        Font fontTitulo = new Font(Font.COURIER,16,Font.BOLD, Color.darkGray);
        paragraph1.add(new Phrase("FACTURA: "+factura.getId(),fontTitulo));
        paragraph1.setAlignment(Element.ALIGN_RIGHT);
        paragraph1.add(new Phrase(Chunk.NEWLINE));
        paragraph1.add(new Phrase(Chunk.NEWLINE));
        document.add(paragraph1);

        Image logo = Image.getInstance("C:\\Users\\amo-l\\Downloads\\logoGris.png");
        logo.setAbsolutePosition(200,600);
        logo.getImageMask();
        document.add(logo);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setBackgroundColor(Color.lightGray);
        table.addCell("Product");
        table.addCell("Quantity of products");
        table.addCell("Unit price");
        table.addCell("Amount");


        Set<Product> products = recipeToCreateDTO.getProducts().stream().map(productToPurchaseDTO -> productService.getProduct(productToPurchaseDTO.getIdProducto())).collect(Collectors.toSet());
        Set<Motorcycle> motorcycles = recipeToCreateDTO.getMotors().stream().map(motoToPurchaseDTO -> motorcycleService.getMoto(motoToPurchaseDTO.getId())).collect(Collectors.toSet());

        Set<Double> subTotalMotos = new HashSet<>();
        if (motorcycles.size()>0){
            motorcycles.forEach(motorcycle -> {

                int cantidadMotoComprar = recipeToCreateDTO.getMotors().stream().filter(moto -> moto.getId() == motorcycle.getId()).findFirst().orElseThrow().getCantidad();
               double amount = cantidadMotoComprar*motorcycle.getPrice();
                subTotalMotos.add(amount);

               PdfPCell c1 = new PdfPCell(new Phrase(motorcycle.getModel()));
               PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(cantidadMotoComprar)));
               PdfPCell c3 = new PdfPCell(new Phrase(String.valueOf(motorcycle.getPrice())));
               PdfPCell c4 = new PdfPCell(new Phrase(String.valueOf(amount)));
               c1.setHorizontalAlignment(Element.ALIGN_CENTER);
               c2.setHorizontalAlignment(Element.ALIGN_CENTER);
               c3.setHorizontalAlignment(Element.ALIGN_CENTER);
               c4.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(c1);
               table.addCell(c2);
               table.addCell(c3);
               table.addCell(c4);
            });
        }

        Set<Double> subtotalProductos = new HashSet<>();
        if(products.size()>0){
            products.forEach(product -> {
                int cantidadComprar = recipeToCreateDTO.getProducts().stream().filter(prod -> prod.getIdProducto() == product.getId()).findFirst().orElseThrow().getCantidad();
                double amount = cantidadComprar* product.getPrice();
                subtotalProductos.add(amount);

                PdfPCell c1 = new PdfPCell(new Phrase(product.getType()));
                PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(cantidadComprar)));
                PdfPCell c3 = new PdfPCell(new Phrase(String.valueOf(product.getPrice())));
                PdfPCell c4 = new PdfPCell(new Phrase(String.valueOf(amount)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
                table.addCell(c4);

            });
        }




        Double totalProducts = subtotalProductos.stream().reduce(Double::sum).orElse(0.0);
        Double totalMotos = subTotalMotos.stream().reduce(Double::sum).orElse(0.0);;
        double totalCompra=(totalProducts + totalMotos);


        table.addCell("");
        table.addCell("");
        table.addCell("Total: ");
        table.addCell(String.valueOf(totalCompra));




        document.add(table);
        document.close();

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

*/

}
