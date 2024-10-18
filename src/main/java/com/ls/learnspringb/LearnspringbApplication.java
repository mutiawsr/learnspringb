package com.ls.learnspringb;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.entities.Product;
import com.ls.learnspringb.entities.Variant;
import com.ls.learnspringb.repositories.CategoryRepository;
import com.ls.learnspringb.repositories.ProductRepository;
import com.ls.learnspringb.repositories.VariantRepository;

@SpringBootApplication
public class LearnspringbApplication {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	VariantRepository variantRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearnspringbApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			Category mainCourse = new Category("Main Course", "main-course", "Description of Main Course");
			Category beverage = new Category("Beverage", "beverage", "Description of Beverage");
			Category dessert = new Category("Dessert", "dessert", "Description of Dessert");

			categoryRepository.save(mainCourse);
			categoryRepository.save(beverage);
			categoryRepository.save(dessert);

			Product meatball = new Product("Meatball", "meatball", "Meatball Product", 1L, false);
			Product noodle = new Product("Noodle", "noodle", "Noodle Product", 1L, false);
			Product friedRice = new Product("Fried Rice", "fried-rice", "Fried Rice Product", 1L, false);
			Product steak = new Product("Steak", "steak", "Steak Product", 1L, false);
			Product mineralWater = new Product("Mineral Water", "mineral-water", "Mineral Water Product", 2L, false);
			Product coffee = new Product("Coffee", "coffee", "Coffee Product", 2L, false);
			Product iceCream = new Product("Ice Cream", "ice-cream", "Ice Cream Product", 3L, false);
			Product pudding = new Product("Pudding", "pudding", "Pudding Product", 3L, false);
			Product waffle = new Product("Waffle", "waffle", "Waffle Product", 3L, false);

			productRepository.save(meatball);
			productRepository.save(noodle);
			productRepository.save(friedRice);
			productRepository.save(steak);
			productRepository.save(mineralWater);
			productRepository.save(coffee);
			productRepository.save(iceCream);
			productRepository.save(pudding);
			productRepository.save(waffle);

			Variant spicyMeatball = new Variant("Spicy Meatball", "spicy-meatball", "Spicy Meatball Variant", BigDecimal.valueOf(15000), BigDecimal.valueOf(20), 1L, false);
			Variant cheesyMeatball = new Variant("Cheesy Meatball", "cheesy-meatball", "Cheesy Meatball Variant", BigDecimal.valueOf(17000), BigDecimal.valueOf(15), 1L, false);
			Variant bbqMeatball = new Variant("BBQ Meatball", "bbq-meatball", "BBQ Meatball Variant", BigDecimal.valueOf(18000), BigDecimal.valueOf(12), 1L, false);
			Variant chickenNoodle = new Variant("Chicken Noodle", "chicken-noodle", "Chicken Noodle Variant", BigDecimal.valueOf(13000), BigDecimal.valueOf(25), 2L, false);
			Variant beefNoodle = new Variant("Beef Noodle", "beef-noodle", "Beef Noodle Variant", BigDecimal.valueOf(15000), BigDecimal.valueOf(20), 2L, false);
			Variant chickenFriedRice = new Variant("Chicken Fried Rice", "chicken-fried-rice", "Chicken Fried Rice Variant", BigDecimal.valueOf(15000), BigDecimal.valueOf(15), 3L, false);
			Variant seafoodFriedRice = new Variant("Seafood Fried Rice", "seafood-fried-rice", "Seafood Fried Rice Variant", BigDecimal.valueOf(18000), BigDecimal.valueOf(10), 3L, false);
			Variant ribeyeSteak = new Variant("Ribeye Steak", "ribeye-steak", "Ribeye Steak Variant", BigDecimal.valueOf(50000), BigDecimal.valueOf(8), 4L, false);
			Variant tBoneSteak = new Variant("T-Bone Steak", "t-bone-steak", "T-Bone Steak Variant", BigDecimal.valueOf(55000), BigDecimal.valueOf(7), 4L, false);
			Variant sirloinSteak = new Variant("Sirloin Steak", "sirloin-steak", "Sirloin Steak Variant", BigDecimal.valueOf(48000), BigDecimal.valueOf(10), 4L, false);
			Variant water600 = new Variant("Mineral Water 600ml", "water-600", "600ml Mineral Water Variant", BigDecimal.valueOf(5000), BigDecimal.valueOf(20), 5L, false);
			Variant water1500 = new Variant("Mineral Water 1500ml", "water-1500", "1500ml Mineral Water Variant", BigDecimal.valueOf(15000), BigDecimal.valueOf(10), 5L, false);
			Variant blackCoffee = new Variant("Black Coffee", "black-coffee", "Black Coffee Variant", BigDecimal.valueOf(10000), BigDecimal.valueOf(50), 6L, false);
			Variant latte = new Variant("Latte", "latte", "Latte Coffee Variant", BigDecimal.valueOf(15000), BigDecimal.valueOf(30), 6L, false);
			Variant cappuccino = new Variant("Cappuccino", "cappuccino", "Cappuccino Coffee Variant", BigDecimal.valueOf(16000), BigDecimal.valueOf(25), 6L, false);
			Variant vanillaIceCream = new Variant("Vanilla Ice Cream", "vanilla-ice-cream", "Vanilla Ice Cream Variant", BigDecimal.valueOf(8000), BigDecimal.valueOf(40), 7L, false);
			Variant chocolateIceCream = new Variant("Chocolate Ice Cream", "chocolate-ice-cream", "Chocolate Ice Cream Variant", BigDecimal.valueOf(8500), BigDecimal.valueOf(35), 7L, false);
			Variant strawberryIceCream = new Variant("Strawberry Ice Cream", "strawberry-ice-cream", "Strawberry Ice Cream Variant", BigDecimal.valueOf(8500), BigDecimal.valueOf(32), 7L, false);
			Variant vanillaPudding = new Variant("Vanilla Pudding", "vanilla-pudding", "Vanilla Pudding Variant", BigDecimal.valueOf(10000), BigDecimal.valueOf(45), 8L, false);
			Variant chocolatePudding = new Variant("Chocolate Pudding", "chocolate-pudding", "Chocolate Pudding Variant", BigDecimal.valueOf(11000), BigDecimal.valueOf(40), 8L, false);
			Variant plainWaffle = new Variant("Plain Waffle", "plain-waffle", "Plain Waffle Variant", BigDecimal.valueOf(12000), BigDecimal.valueOf(30), 9L, false);
			Variant chocolateWaffle = new Variant("Chocolate Waffle", "chocolate-waffle", "Chocolate Waffle Variant", BigDecimal.valueOf(14000), BigDecimal.valueOf(25), 9L, false);
			Variant strawberryWaffle = new Variant("Strawberry Waffle", "strawberry-waffle", "Strawberry Waffle Variant", BigDecimal.valueOf(14000), BigDecimal.valueOf(20), 9L, false);

			variantRepository.save(spicyMeatball);
			variantRepository.save(cheesyMeatball);
			variantRepository.save(bbqMeatball);
			variantRepository.save(chickenNoodle);
			variantRepository.save(beefNoodle);
			variantRepository.save(chickenFriedRice);
			variantRepository.save(seafoodFriedRice);
			variantRepository.save(ribeyeSteak);
			variantRepository.save(tBoneSteak);
			variantRepository.save(sirloinSteak);
			variantRepository.save(water600);
			variantRepository.save(water1500);
			variantRepository.save(blackCoffee);
			variantRepository.save(latte);
			variantRepository.save(cappuccino);
			variantRepository.save(vanillaIceCream);
			variantRepository.save(chocolateIceCream);
			variantRepository.save(strawberryIceCream);
			variantRepository.save(vanillaPudding);
			variantRepository.save(chocolatePudding);
			variantRepository.save(plainWaffle);
			variantRepository.save(chocolateWaffle);
			variantRepository.save(strawberryWaffle);

		};
	}
	
}
