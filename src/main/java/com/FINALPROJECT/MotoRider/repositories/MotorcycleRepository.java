package com.FINALPROJECT.MotoRider.repositories;

import com.FINALPROJECT.MotoRider.models.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
}
