package com.FINALPROJECT.MotoRider.services;

import com.FINALPROJECT.MotoRider.dto.MotorcycleDTO;
import com.FINALPROJECT.MotoRider.models.Motorcycle;

import java.util.List;

public interface MotorcycleService {

    List<MotorcycleDTO> getMotorcyclesDTO();

    void saveMotorcycle(Motorcycle motorcycle);


}
