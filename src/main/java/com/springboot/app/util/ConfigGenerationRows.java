package com.springboot.app.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class ConfigGenerationRows {

	@Bean
	public Faker getFaker() {
		return new Faker();
	}

}
