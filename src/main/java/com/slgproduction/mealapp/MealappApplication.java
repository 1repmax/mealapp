package com.slgproduction.mealapp;

import com.slgproduction.mealapp.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MealappApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MealappApplication.class, args);
	}

	/**
	 * This method is used for war file build
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(MealappApplication.class);
	}

}
