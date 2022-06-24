package com.FINALPROJECT.MotoRider.repositories;

import com.FINALPROJECT.MotoRider.models.MotorcyclePurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MotorcyclePurchaseOrderRepository extends JpaRepository<MotorcyclePurchaseOrder,Long> {

}
