package com.FINALPROJECT.MotoRider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.FINALPROJECT.MotoRider.models.Client;



@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);
   // Client findByUserName(String userName);
    Client getById(long id);
    Client findByToken(String token);

};
