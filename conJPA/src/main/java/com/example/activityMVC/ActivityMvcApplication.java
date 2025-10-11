package com.example.activityMVC;

import com.example.activityMVC.model.Usuario;
import com.example.activityMVC.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActivityMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityMvcApplication.class, args);
    }

    // Insert sample data on startup
    @Bean
    CommandLineRunner initData(UsuarioRepository usuarioRepository) {
        return args -> {
            usuarioRepository.save(new Usuario("Edwin", "Piña"));
            usuarioRepository.save(new Usuario("Ana", "Gómez"));
        };
    }
}
