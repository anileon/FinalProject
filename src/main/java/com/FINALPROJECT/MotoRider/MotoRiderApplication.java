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

			Motorcycle motorcycleDucati1 = new Motorcycle("Multistrada-V2", BrandType.DUCATI,"995cc", List.of("https://i.ibb.co/XpfK2XL/ducati-Multistrada-V2.png","https://images.ctfassets.net/x7j9qwvpvr5s/2NNJvlilcpSvY6sQuIH35G/482785c6c91a7bf18bdf975e97a5a0d8/Model-Menu-MY22-MTS-V2-v06.png"),30050,5);
			motorcycleRepository.save(motorcycleDucati1);

			Motorcycle motorcycleDucati2 = new Motorcycle("Scrambler-Urban",BrandType.DUCATI,"1250cc", List.of("https://i.ibb.co/1Mw5jp2/ducati-Scrambler-Urban-Motard-removebg-preview.png","https://i0.wp.com/ducatisantaanita.com/wp-content/uploads/2022/03/Scrambler-Ducati-Urban-Motard-360-Side-Right-v2.png"),30000,5);
			motorcycleRepository.save (motorcycleDucati2);

			Motorcycle motorcycleDucati3 = new Motorcycle("Cafe Racer Scrambler",BrandType.DUCATI, "1400cc", List.of("https://i.ibb.co/q1bkWkn/Ducati-Scrambler-Cafe-Racer-2019-1-removebg-preview.png",""),31000,4);
			motorcycleRepository.save(motorcycleDucati3);

			Motorcycle motorcycleDucati4 = new Motorcycle("Diavel Lamborghini",BrandType.DUCATI,"1262cc", List.of("https://i.ibb.co/x2qjNLs/ducati-Diavel-1260-Lamborghini1-removebg-preview.png","https://i.ibb.co/Pm4ScRB/ducati-Diavel-1260-Lamborghini2-removebg-preview.png"),32500,5);
			motorcycleRepository.save(motorcycleDucati4);

			Motorcycle motorcycleDucati5 = new Motorcycle ("Hypermota", BrandType.DUCATI,"1250cc", List.of("https://i.ibb.co/xLGQG0F/ducati-Hypermota-d-950-SP-removebg-preview.png", "https://i.ibb.co/YThW5v4/1366-2000-removebg-preview.png"),30000,5);
			motorcycleRepository.save(motorcycleDucati5);

			Motorcycle motorcycleDucati6 = new Motorcycle("Panigale-V4",BrandType.DUCATI,"995cc",List.of("https://i.ibb.co/y0qZdxB/ducati-Panigale-V4-removebg-preview.png", "https://images.ctfassets.net/x7j9qwvpvr5s/D9I2R8gPHkQrQhkGhJJFh/e2005f219da76fcefe4d3d6947e77139/Panigale-V4-MY20-Model-Preview-1050x650.png"),30000,5);
			motorcycleRepository.save(motorcycleDucati6);

			Motorcycle motorcycleDucati7 = new Motorcycle("Scrambler-1100", BrandType.DUCATI,"995cc", List.of("https://i.ibb.co/PYYtHQc/ducati-Scrambler-1100-removebg-preview.png","https://i0.wp.com/ducatisantaanita.com/wp-content/uploads/2022/03/Scrambler-Ducati-1100-Tribute-Pro-360-Side-Right-v2.png?resize=1080%2C720&ssl=1"),30000,5);
			motorcycleRepository.save(motorcycleDucati7);

			Motorcycle motorcycleDucati8 = new Motorcycle("Super-Sport-S",BrandType.DUCATI, "1400cc", List.of("https://i.ibb.co/tJqwVyL/ducati-Super-Sport-S-removebg-preview.png","https://i.ibb.co/JxYXbMz/Supersport-MY18-Grey-02-Slider-Gallery-1920x1080-removebg-preview.png"),30000,5);
			motorcycleRepository.save(motorcycleDucati8);

			Motorcycle motorcycleDucati9 = new Motorcycle("XDiavel-Neral",BrandType.DUCATI,"995cc",List.of("https://i.ibb.co/64C2R5w/ducati-Diavel-XDiavel-Neral-Nera1-removebg-preview.png","https://i.ibb.co/txrSb35/ducati-Diavel-XDiavel-Neral-Nera2-removebg-preview.png","https://ducatisantaanita.com/wp-content/uploads/2022/03/XDiavel-Nera-ID-MY22-Model-Preview-1050x650-1.png"),30000,5);
			motorcycleRepository.save(motorcycleDucati9);

			Motorcycle motorcycleDucati10 = new Motorcycle("Desert-X", BrandType.DUCATI, "1200cc", List.of("https://i0.wp.com/ducatisantaanita.com/wp-content/uploads/2022/03/MY-22-DesertX-Model-Blocks-630x390-1.png","https://i0.wp.com/ducatisantaanita.com/wp-content/uploads/2022/03/MY-22-DesertX-Model-Blocks-630x390-1.png?w=630&ssl=1"),30000,5);
			motorcycleRepository.save(motorcycleDucati10);


			Motorcycle motorcycleHarley1 = new Motorcycle("Fat-Bob2018", BrandType.HARLEY,"1250cc", List.of("https://i.ibb.co/XS8NZMv/harley2.png", "https://i.ibb.co/7RHbBvC/2000000023-removebg-preview.png "),30000,5);
			motorcycleRepository.save(motorcycleHarley1);

			Motorcycle motorcycleHarley2 = new Motorcycle("Forty-Eight-Special",BrandType.HARLEY,"995cc",List.of("https://i.ibb.co/WnG5Rjf/harley3.png","https://i.ibb.co/7pQxPHC/b601f47e91490965441f7120a18b1d1c-removebg-preview.png"),30000,5);
			motorcycleRepository.save(motorcycleHarley2);

			Motorcycle motorcycleHarley3 = new Motorcycle("Pan-Americana-Special", BrandType.HARLEY, "1250cc", List.of("https://i.ibb.co/3BrTFtQ/harley4.png","https://i.ibb.co/QY6KLmX/2000000004-removebg-preview.png"),30000,5);
			motorcycleRepository.save(motorcycleHarley3);

			Motorcycle motorcycleHarley4 = new Motorcycle("Street-Bobtm", BrandType.HARLEY,"1250cc", List.of("https://i.ibb.co/p1PmLBk/harley5.png","https://i.ibb.co/TwXS0w9/280e913182aed25aedd77632bc113f37-removebg-preview.png"),30000,5);
			motorcycleRepository.save(motorcycleHarley4);

			Motorcycle motorcycleHarley5 = new Motorcycle("Pan-America", BrandType.HARLEY, "1200cc",List.of("https://i.ibb.co/pxmHhv7/Harley-Davidson-Pan-America.png", "https://i.ibb.co/wBXxYMb/01-harley-davidson-pan-america-1250-special-2021-estudio-naranja-739-a-removebg-preview.png"),31500,5);
			motorcycleRepository.save(motorcycleHarley5);

			Motorcycle motorcycleHarley6 = new Motorcycle("Road-King-Special",BrandType.HARLEY, "1300cc", List.of("https://i.ibb.co/C64TvPJ/Harley-Davidson-Road-King-Special-removebg-preview.png","https://i.ibb.co/JsX2Gzr/193-1933356-orange-and-black-harley-davidson-motorcycle-2018-harley-removebg-preview.png"), 32000,5);
			motorcycleRepository.save(motorcycleHarley6);

			Motorcycle motorcycleHarley7 = new Motorcycle("Roadster", BrandType.HARLEY, "1200cc", List.of("https://i.ibb.co/x574Ymg/Harley-Davidson-Roadster.png","https://i.ibb.co/vV2P2PG/original-removebg-preview.png"),31500,5);
			motorcycleRepository.save(motorcycleHarley7);

			Motorcycle motorcycleHarley8 = new Motorcycle("Live-Wire 2020",BrandType.HARLEY,"1150cc", List.of("https://i.ibb.co/TvMb3f3/harley-Davidson-Live-Wire.png","https://i.ibb.co/rfCC3N3/Harley-Davidson-Live-Wire-1130x753-removebg-preview.png"),30000,5);
			motorcycleRepository.save(motorcycleHarley8);

			Motorcycle motorcycleHarley9 = new Motorcycle("Live-Wire 2022",BrandType.HARLEY,"1350cc",List.of("https://i.ibb.co/2hjsz4v/harley-Davidson-Live-Wire.png","https://i.ibb.co/rfCC3N3/Harley-Davidson-Live-Wire-1130x753-removebg-preview.png"),31500,5);
			motorcycleRepository.save(motorcycleHarley9);

			Motorcycle motorcycleHarley10 = new Motorcycle("Low-rider", BrandType.HARLEY,"1250cc",List.of("https://i.ibb.co/hYk26mG/harley2022-low-rider-s-010-motorcycle.png","https://i.ibb.co/0tZYjB0/Low-Rider-S-2019-Beitragsbild-removebg-preview.png"),32500,5);
			motorcycleRepository.save(motorcycleHarley10);


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







			Client client1 = new Client("Augusto", "Casanova", "Augusto@gmail.com", "calle siempre viva 465", 1140325063, "hola");
			clientRepository.save(client1);




		};
	}
}
