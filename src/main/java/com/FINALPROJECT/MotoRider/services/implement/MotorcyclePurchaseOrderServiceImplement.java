package com.FINALPROJECT.MotoRider.services.implement;

import com.FINALPROJECT.MotoRider.dto.MotorcyclePurchaseOrderDTO;
import com.FINALPROJECT.MotoRider.models.MotorcyclePurchaseOrder;
import com.FINALPROJECT.MotoRider.repositories.MotorcyclePurchaseOrderRepository;
import com.FINALPROJECT.MotoRider.services.MotorcyclePurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MotorcyclePurchaseOrderServiceImplement implements MotorcyclePurchaseOrderService {
    @Autowired
    MotorcyclePurchaseOrderRepository motorcyclePurchaseOrderRepository;

    @Override
    public List<MotorcyclePurchaseOrderDTO> getMotorcyclesPurchaseOrdersDTO() {
        return null;
    }

    @Override
    public void saveMotorcyclePurchaseOrder(MotorcyclePurchaseOrder motorcyclePurchaseOrder) {
        motorcyclePurchaseOrderRepository.save(motorcyclePurchaseOrder);
    }
}
