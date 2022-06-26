package com.FINALPROJECT.MotoRider.services;

import com.FINALPROJECT.MotoRider.dto.MotorcycleDTO;
import com.FINALPROJECT.MotoRider.models.Motorcycle;

import java.util.List;

public interface MotorcycleService {

    List<MotorcycleDTO> getMotorcyclesDTO();

    MotorcycleDTO getMotoDto(long id);

    void saveMotorcycle(Motorcycle motorcycle);

    Motorcycle getMoto(long id);


}
