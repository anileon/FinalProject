package com.FINALPROJECT.MotoRider.services;


import com.FINALPROJECT.MotoRider.dto.MotorcyclePurchaseOrderDTO;
import com.FINALPROJECT.MotoRider.models.MotorcyclePurchaseOrder;

import java.util.List;

public interface MotorcyclePurchaseOrderService {

    List<MotorcyclePurchaseOrderDTO> getMotorcyclesPurchaseOrdersDTO();

    void saveMotorcyclePurchaseOrder(MotorcyclePurchaseOrder motorcyclePurchaseOrder);

}
