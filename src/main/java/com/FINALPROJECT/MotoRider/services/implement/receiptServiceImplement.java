package com.FINALPROJECT.MotoRider.services.implement;

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
}
