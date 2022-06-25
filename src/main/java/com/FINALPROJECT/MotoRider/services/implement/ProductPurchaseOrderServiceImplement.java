package com.FINALPROJECT.MotoRider.services.implement;


import com.FINALPROJECT.MotoRider.models.ProductPurchaseOrder;
import com.FINALPROJECT.MotoRider.repositories.ProductPurchaseOrderRepository;
import com.FINALPROJECT.MotoRider.services.ProductPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductPurchaseOrderServiceImplement implements ProductPurchaseOrderService {


    @Autowired
    ProductPurchaseOrderRepository productPurchaseOrderRepository;
    @Override
    public void saveProductPurchaseOrder(ProductPurchaseOrder productPurchaseOrder) {
        productPurchaseOrderRepository.save(productPurchaseOrder);
    }
}
