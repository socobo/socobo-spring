package com.socobo;

import com.socobo.home.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocoboApplication {

	public static void main(String[] args) {

		SpringApplication.run(HomeController.class, args);
	}
}
