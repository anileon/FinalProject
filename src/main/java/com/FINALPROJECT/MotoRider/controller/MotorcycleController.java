package com.FINALPROJECT.MotoRider.controller;

import com.FINALPROJECT.MotoRider.dto.MotoToAdd;
import com.FINALPROJECT.MotoRider.dto.MotorcycleDTO;
import com.FINALPROJECT.MotoRider.models.Motorcycle;
import com.FINALPROJECT.MotoRider.services.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/admin/moto")
    public ResponseEntity<Object> addStockMoto(@RequestParam long id, @RequestParam int stockAgregar){

    //    Client client (Aca tiene que ser un admin)

        Motorcycle motorcycle = motorcycleService.getMoto(id);
        if(stockAgregar <= 0){
            return new ResponseEntity<>("Invalid stock", HttpStatus.FORBIDDEN);
        }

        motorcycle.setStock(motorcycle.getStock() + stockAgregar);

        motorcycleService.saveMotorcycle(motorcycle);

        return new ResponseEntity<>("Stock Updated",HttpStatus.ACCEPTED);
    };

    @PostMapping("/admin/moto")
    public ResponseEntity<Object> addMoto(@RequestBody MotoToAdd motoToAdd){

        //    Client client (Aca tiene que ser un admin)

        if(motoToAdd == null){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (motoToAdd.getDisplacement().isEmpty()){
            return new ResponseEntity<>("missing cc", HttpStatus.FORBIDDEN);
        }
        if(motoToAdd.getModel().isEmpty()){
            return new ResponseEntity<>("Missing motorcicle model", HttpStatus.FORBIDDEN);
        }
        if(motoToAdd.getBrandType() == null){
            return new ResponseEntity<>("Missing brand-type", HttpStatus.I_AM_A_TEAPOT);
        }
        if(motoToAdd.getPrice() <= 0){
            return new ResponseEntity<>("Price can not be 0 or less", HttpStatus.FORBIDDEN);
        }
        if(motoToAdd.getImages().isEmpty()){
            return new ResponseEntity<>("Missing images", HttpStatus.FORBIDDEN);
        }

        Motorcycle motorcycle = new Motorcycle(motoToAdd.getModel(), motoToAdd.getBrandType(), motoToAdd.getDisplacement()
                , motoToAdd.getImages(), motoToAdd.getPrice(), motoToAdd.getStock());
        motorcycleService.saveMotorcycle(motorcycle);

        return new ResponseEntity<>("Added new moto", HttpStatus.ACCEPTED);

    }

}
