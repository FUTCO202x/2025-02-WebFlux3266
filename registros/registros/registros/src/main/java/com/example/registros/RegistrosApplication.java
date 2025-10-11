package com.example.registros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.registros.usuarios")
public class RegistrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrosApplication.class, args);
	}
}
