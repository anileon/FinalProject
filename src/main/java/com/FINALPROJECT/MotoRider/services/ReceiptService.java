package com.FINALPROJECT.MotoRider.services;

import com.FINALPROJECT.MotoRider.dto.MotorcycleDTO;
import com.FINALPROJECT.MotoRider.dto.ReceiptDTO;
import com.FINALPROJECT.MotoRider.models.Motorcycle;
import com.FINALPROJECT.MotoRider.models.Receipt;

import java.util.Set;

public interface ReceiptService {

    void saveReceipt(Receipt receipt);

    ReceiptDTO getReceiptDto(long id);

    Receipt getReceipt(long id);

    Set<ReceiptDTO> getReceiptsDto();

}
