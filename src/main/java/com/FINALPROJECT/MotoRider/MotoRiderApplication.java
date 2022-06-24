package com.FINALPROJECT.MotoRider;

import com.FINALPROJECT.MotoRider.models.BrandType;
import com.FINALPROJECT.MotoRider.models.Motorcycle;
import com.FINALPROJECT.MotoRider.repositories.MotorcycleRepository;
import com.FINALPROJECT.MotoRider.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MotoRiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoRiderApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(MotorcycleRepository motorcycleRepository, ProductRepository productRepository){
		return (args) -> {

			Motorcycle motorcycle1 = new Motorcycle("Streetfighter v2", BrandType.DUCATI,"995 cc", List.of("https://images.ctfassets.net/x7j9qwvpvr5s/3k4PLRq2dHWcOFNJWhsxMu/22a0aeab23f79dd9b9e7255a3339e034/Streetfighter-V2-Storm-Grey-01-gallery-1920x1080.jpg","https://images.ctfassets.net/x7j9qwvpvr5s/QG80R1hdpbK11FgyNCKG1/058a6ce06cdb6f975d87a6069711aa01/Streetfighter-V2-Storm-Grey-02-gallery-1920x1080.jpg","https://images.ctfassets.net/x7j9qwvpvr5s/otQI74xuqdQ2GD7ujJo70/fa41e4ece58df6d662b85844fd1a4fcb/Streetfighter-V2-Storm-Grey-05-gallery-1920x1080.jpg"),1250.00,5);
			motorcycleRepository.save(motorcycle1);
		};
	}
}
