package com.FINALPROJECT.MotoRider.controller;

import com.FINALPROJECT.MotoRider.dto.MotorcycleDTO;
import com.FINALPROJECT.MotoRider.services.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MotorcycleController {

    @Autowired
    MotorcycleService motorcycleService;

    @GetMapping("/motorcycles")
    public List<MotorcycleDTO> getMotorcycles(){
        return motorcycleService.getMotorcyclesDTO();
    }


    @GetMapping("/motorcycles/{id}")
    public MotorcycleDTO getMotoDto(@PathVariable long id){

        return motorcycleService.getMotoDto(id);
    }

}
