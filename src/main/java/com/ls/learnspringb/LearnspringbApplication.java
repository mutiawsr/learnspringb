package com.ls.learnspringb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.entities.Product;
import com.ls.learnspringb.repositories.CategoryRepository;
import com.ls.learnspringb.repositories.ProductRepository;

@SpringBootApplication
public class LearnspringbApplication {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearnspringbApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			Category food = new Category("food", "food", "description of food");
			Category car = new Category("car", "car", "description of car");
			Category book = new Category("book", "book", "description of book");

			categoryRepository.save(food);
			categoryRepository.save(car);
			categoryRepository.save(book);

			Product bmw = new Product("BMW", "bmw", "Luxury car", 2L, false);
			Product tesla = new Product("Tesla", "tesla", "Electric car", 2L, false);
			Product indomie = new Product("Indomie", "indomie", "Instant Noodle", 1L, false);
			Product harryPotter = new Product("Harry Potter", "harry-potter", "Fantasy Fiction Book", 3L, false);
			Product doraemon = new Product("Doraemon", "doraemon", "Japanese Comic Book", 3L, false);
			Product alice = new Product("Alice in Wonderland", "alice-in-wonderland", "English Children Novel", 3L, false);

			productRepository.save(bmw);
			productRepository.save(tesla);
			productRepository.save(indomie);
			productRepository.save(harryPotter);
			productRepository.save(doraemon);
			productRepository.save(alice);

		};
	}
	
}
