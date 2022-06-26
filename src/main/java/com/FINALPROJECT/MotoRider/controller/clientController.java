package com.FINALPROJECT.MotoRider.controller;


import com.FINALPROJECT.MotoRider.dto.ClientDTO;
import com.FINALPROJECT.MotoRider.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class clientController {

    @Autowired
    ClientService clientService;
    @GetMapping("/clients")
    Set<ClientDTO> getAll (){
        return clientService.getAllClientsDTO();
    }

    @GetMapping("/clients/{id}")
    ClientDTO getClient(@PathVariable long id){
        return clientService.getClientDTO(id);
    }

}
