package com.FINALPROJECT.MotoRider.services.implement;


import com.FINALPROJECT.MotoRider.dto.ClientDTO;
import com.FINALPROJECT.MotoRider.repositories.ClientRepository;
import com.FINALPROJECT.MotoRider.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Override
    public ClientDTO getClientDTO(long id) {
        return clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
    }

    @Override
    public Set<ClientDTO> getAllClientsDTO() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toSet());
    }
}
