package com.FINALPROJECT.MotoRider.services.implement;


import com.FINALPROJECT.MotoRider.dto.ClientDTO;
import com.FINALPROJECT.MotoRider.models.Client;
import com.FINALPROJECT.MotoRider.repositories.ClientRepository;
import com.FINALPROJECT.MotoRider.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @Override
    public Client getClient(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client getClientCredential(String credential) {
            return clientRepository.findByEmail(credential);

    }

    @Override
    public Client getClientToken(String token) {
        return clientRepository.findByToken(token);
    }

    @Override
    public boolean existClient(long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client getCurrent(Authentication authentication) {
        return clientRepository.findByEmail(authentication.getName());
    }

    @Override
    public ClientDTO getCurrentDTO(Authentication authentication) {
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }
}
