package com.FINALPROJECT.MotoRider.services;

import com.FINALPROJECT.MotoRider.dto.ClientDTO;
import com.FINALPROJECT.MotoRider.models.Client;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface ClientService {

    ClientDTO getClientDTO(long id);

    Set<ClientDTO> getAllClientsDTO();

    Client getClient(long id);

    Client getClientCredential(String credential);
    Client getClientToken(String token);

    boolean existClient(long id);

    void saveClient(Client client);

    Client getCurrent(Authentication authentication);



}
