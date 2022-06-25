package com.FINALPROJECT.MotoRider.services;

import com.FINALPROJECT.MotoRider.dto.ClientDTO;
import com.FINALPROJECT.MotoRider.models.Client;

import java.util.Set;

public interface ClientService {

    ClientDTO getClientDTO(long id);

    Set<ClientDTO> getAllClientsDTO();

    Client getClient(long id);


}
