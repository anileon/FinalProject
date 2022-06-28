package com.FINALPROJECT.MotoRider;

import com.FINALPROJECT.MotoRider.models.*;
import com.FINALPROJECT.MotoRider.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MotoRiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoRiderApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(MotorcycleRepository motorcycleRepository, ProductRepository productRepository,
									  ClientRepository clientRepository, ProductPurchaseOrderRepository productPurchaseOrderRepository, ReceiptRepository receiptRepository){
		return (args) -> {

			Motorcycle motorcycle1 = new Motorcycle("Streetfighter v2", BrandType.DUCATI,"995 cc", List.of("https://images.ctfassets.net/x7j9qwvpvr5s/3k4PLRq2dHWcOFNJWhsxMu/22a0aeab23f79dd9b9e7255a3339e034/Streetfighter-V2-Storm-Grey-01-gallery-1920x1080.jpg","https://images.ctfassets.net/x7j9qwvpvr5s/QG80R1hdpbK11FgyNCKG1/058a6ce06cdb6f975d87a6069711aa01/Streetfighter-V2-Storm-Grey-02-gallery-1920x1080.jpg","https://images.ctfassets.net/x7j9qwvpvr5s/otQI74xuqdQ2GD7ujJo70/fa41e4ece58df6d662b85844fd1a4fcb/Streetfighter-V2-Storm-Grey-05-gallery-1920x1080.jpg"),1250.00,5);
			motorcycleRepository.save(motorcycle1);

			Product campera1 = new Product("Jacket", GenderType.FEMALE, "leather Jacket sleeves less", 200, List.of("https://i.ibb.co/6sTM48r/97027-22-VW-F-removebg-preview.png", "https://i.ibb.co/F8zbJdw/97027-22-VW-B-removebg-preview.png"),
					10, 2);
			productRepository.save(campera1);
			Product campera2 = new Product("Jacket", GenderType.FEMALE, "Leather jacket with flowers", 250, List.of("https://i.ibb.co/H2BnVCy/97023-22-VW-F-removebg-preview.png", "https://i.ibb.co/5GqwzqG/97023-22-VW-B-removebg-preview.png"),
					7, 3);
			productRepository.save(campera2);
			Product campera3 = new Product("Jacket", GenderType.FEMALE, "Pink leather Jacket", 300, List.of("https://i.ibb.co/M1SGhmt/97033-22-VW-F-removebg-preview.png", "https://i.ibb.co/bzrgpFw/97033-22-VW-B-removebg-preview.png"),
					9, 1);
			productRepository.save(campera3);
			Product campera4 = new Product("Jacket", GenderType.FEMALE, "Beige leather jacket", 275.50, List.of("https://i.ibb.co/M774kFt/97001-22-VW-F-removebg-preview.png", "https://i.ibb.co/ccttTF8/97001-22-VW-B-removebg-preview.png"),
					5, 3);
			productRepository.save(campera4);

			Product casco1 = new Product("Helmet", GenderType.FEMALE, "Black helmet with pink lines", 150, List.of("https://i.ibb.co/9GBjxDP/97134-22-VX-F-removebg-preview.png", "https://i.ibb.co/h7QXq9J/97134-22-VX-ALT01-removebg-preview.png"),
					3, 2);
			productRepository.save(casco1);
			Product casco2 = new Product("Helmet", GenderType.FEMALE, "White helmet with black words", 175.45, List.of("https://i.ibb.co/zhQRZRc/98101-20-VX-ALT01-removebg-preview.png", "https://i.ibb.co/jvNvwcr/98101-20-VX-F-removebg-preview.png"),
					2, 3);
			productRepository.save(casco2);
			Product casco3 = new Product("Helmet", GenderType.FEMALE, "Red Harley Helmet retro style", 400, List.of("https://i.ibb.co/8YFCqyH/97203-22-VX-F-removebg-preview.png", "https://i.ibb.co/Vq37SmQ/97203-22-VX-ALT03-removebg-preview.png"),
					4, 2);
			productRepository.save(casco3);
			Product casco4 = new Product("Helmet", GenderType.FEMALE, "Military type helmet", 321.05, List.of("https://i.ibb.co/gjxqjqx/98110-20-VX-F-removebg-preview.png", "https://i.ibb.co/m0dcWWX/98110-20-VX-ALT05-removebg-preview.png"),
					3,1);
			productRepository.save(casco4);

			Product guantes1 = new Product("Gloves", GenderType.FEMALE, "Leather black gloves", 100, List.of("https://i.ibb.co/3hvCvS8/97116-22-VW-F-removebg-preview.png"),
					2, 1);
			productRepository.save(guantes1);
			Product guantes2 = new Product("Gloves", GenderType.FEMALE, "Leather brown gloves from india", 200, List.of("https://i.ibb.co/PDQpNy9/97117-22-VW-F-removebg-preview.png"),
					3, 2);
			productRepository.save(guantes2);
			Product guantes3 = new Product("Gloves", GenderType.FEMALE, "Leather black gloves without fingers", 150, List.of("https://i.ibb.co/h19Q6DF/97118-22-VW-F-removebg-preview.png"),
					1, 3);
			productRepository.save(guantes3);
			Product guantes4 = new Product("Gloves", GenderType.FEMALE, "Leather black gloves with pink lines", 500, List.of("https://i.ibb.co/BypP4Br/98131-20-VW-F-removebg-preview.png"),
					3, 1);
			productRepository.save(guantes4);

			Product campera5 = new Product("Jacket", GenderType.MALE, "Black Harley leather jacket", 233.33, List.of("https://i.ibb.co/L1pSfFM/97015-22-VM-F-removebg-preview.png", "https://i.ibb.co/znTQSjk/97015-22-VM-B-removebg-preview.png"),
					1, 4);
			productRepository.save(campera5);
			Product campera6 = new Product("Jacket", GenderType.MALE, "Brown Harley leather jacket with our flag", 420.420, List.of("https://i.ibb.co/HNvJrzM/97017-22-VM-F-removebg-preview-1.png", "https://i.ibb.co/h7tpXCf/97017-22-VM-B-removebg-preview.png"),
					3, 5);
			productRepository.save(campera6);
			Product campera7 = new Product("Jacket", GenderType.MALE, "Black and Brown Harley leather jacket", 300, List.of("https://i.ibb.co/z5FkgJj/98053-19-VM-F-removebg-preview.png", "https://i.ibb.co/YBfgb2Y/98053-19-VM-B-removebg-preview.png"),
					4, 4);
			productRepository.save(campera7);
			Product campera8 = new Product("Jacket", GenderType.MALE, "Orange and Black Harley Leather Jacket", 276.99, List.of("https://i.ibb.co/t3C63kw/98112-16-VM-F-removebg-preview.png", "https://i.ibb.co/j5G1dVt/98112-16-VM-B-removebg-preview.png"),
					1, 5);
			productRepository.save(campera8);

			Product guantes5 = new Product("Gloves", GenderType.MALE, "Black harley leather gloves", 100, List.of("https://i.ibb.co/yB2bwZn/98140-22-VM-F-removebg-preview.png"),
					2, 3);
			productRepository.save(guantes5);
			Product guantes6 = new Product("Gloves", GenderType.MALE, "Brown Harley leather gloves", 150, List.of("https://i.ibb.co/ZLx0kX9/98141-22-VM-F-removebg-preview.png"),
					3, 4);
			productRepository.save(guantes6);
			Product guantes7 = new Product("Gloves", GenderType.MALE, "Beige Harley leather gloves", 137.5, List.of("https://i.ibb.co/b5mwJWP/98324-19-VM-F-removebg-preview.png"),
					1, 5);
			productRepository.save(guantes7);

			Product casco5 = new Product("Helmet", GenderType.MALE, "Grey Harley race helmet", 250, List.of("https://i.ibb.co/1ZsTJjb/unknown.png", "https://i.ibb.co/8mX2vFZ/98228-18-VX-ALT02-removebg-preview.png"),
					3, 4);
			productRepository.save(casco5);
			Product casco6 = new Product("Helmet", GenderType.MALE, "Green Harley retro helmet", 300, List.of("https://i.ibb.co/zhvbtnV/97173-22-VX-F-removebg-preview.png", "https://i.ibb.co/RbjxrTv/97173-22-VX-ALT05-removebg-preview.png"),
					2, 5);
			Product casco7 = new Product("Helmet", GenderType.MALE, "Red and Orange Harley race helmet", 275, List.of("https://i.ibb.co/3drpyHV/97176-22-VX-F-removebg-preview.png", "https://i.ibb.co/g7j1FbN/97176-22-VX-ALT03-removebg-preview.png"),
					4, 4);
			productRepository.save(casco6);
			productRepository.save(casco7);







			Client client1 = new Client("Augusto", "Casanova", "Augusto@gmail.com","hola");
			clientRepository.save(client1);




		};
	}
}
