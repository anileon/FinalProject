package com.FINALPROJECT.MotoRider.services.implement;


import com.FINALPROJECT.MotoRider.dto.MotorcycleDTO;
import com.FINALPROJECT.MotoRider.models.Motorcycle;
import com.FINALPROJECT.MotoRider.repositories.MotorcycleRepository;
import com.FINALPROJECT.MotoRider.services.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotorcycleServiceImplement implements MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;
    @Override
    public List<MotorcycleDTO> getMotorcyclesDTO() {
        return motorcycleRepository.findAll().stream().map(motorcycle -> new MotorcycleDTO(motorcycle)).collect(Collectors.toList());
    }

    @Override
    public void saveMotorcycle(Motorcycle motorcycle) {
        motorcycleRepository.save(motorcycle);
    }


}
