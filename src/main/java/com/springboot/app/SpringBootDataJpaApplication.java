package com.springboot.app;



import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.springboot.app.models.dao.IClienteDao;
import com.springboot.app.models.entity.Cliente;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner{
    @Autowired
    private IClienteDao clienteDao;
    @Autowired
    private Faker faker;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        IntStream.range(0,100).forEach(x-> clienteDao.save(new Cliente(
                faker.name().name(), faker.name().lastName(), faker.internet().emailAddress()
        )));
    }
		
	}


