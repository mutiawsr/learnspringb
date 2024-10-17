package com.ls.learnspringb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.repositories.CategoryRepository;

@SpringBootApplication
public class LearnspringbApplication {

	@Autowired
	CategoryRepository categoryRepository;

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
		};
	}
	
}
