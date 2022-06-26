package com.FINALPROJECT.MotoRider.repositories;


import com.FINALPROJECT.MotoRider.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {



}
