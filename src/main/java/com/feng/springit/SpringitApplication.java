package com.feng.springit;

import com.feng.springit.config.SpringitProperies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableConfigurationProperties(SpringitProperies.class)
public class SpringitApplication {

	@Autowired
	private SpringitProperies springitProperies; //inject reference to springit class

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}

	@Bean
	@Profile({"dev","prod"})
	CommandLineRunner runner() {
		return args -> {
			System.out.println("Welcome message " + springitProperies.getWelcomeMsg());
		};
	}
}
