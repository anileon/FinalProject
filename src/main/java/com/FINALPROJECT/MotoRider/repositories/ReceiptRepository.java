package com.FINALPROJECT.MotoRider.repositories;

import com.FINALPROJECT.MotoRider.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {


}
