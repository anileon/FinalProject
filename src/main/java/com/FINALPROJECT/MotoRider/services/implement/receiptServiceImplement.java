package com.FINALPROJECT.MotoRider.services.implement;

import com.FINALPROJECT.MotoRider.dto.ReceiptDTO;
import com.FINALPROJECT.MotoRider.models.Receipt;
import com.FINALPROJECT.MotoRider.repositories.ReceiptRepository;
import com.FINALPROJECT.MotoRider.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class receiptServiceImplement implements ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;
    @Override
    public void saveReceipt(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    @Override
    public ReceiptDTO getReceiptDto(long id) {
        return receiptRepository.findById(id).map(receipt -> new ReceiptDTO(receipt)).orElse(null);
    }

    @Override
    public Receipt getReceipt(long id) {
        return receiptRepository.findById(id).orElse(null);
    }
}
