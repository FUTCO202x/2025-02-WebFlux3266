package com.security.security_inicio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//Import esta libreria
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        //http.authorizeHttpRequests((request)-> request.anyRequest().authenticated());
        //http.authorizeHttpRequests((request)-> request.anyRequest().permitAll());
        //http.authorizeHttpRequests((request)-> request.anyRequest().denyAll());
        http.authorizeHttpRequests((request)-> request
            .requestMatchers("/myAccount","/myBalance","myLoans","/myCards").authenticated()
            .requestMatchers("/notices","/contact","/error").permitAll());
        //http.formLogin(withDefaults());
        http.formLogin(f->f.disable());
        //http.httpBasic(withDefaults());
        http.httpBasic(h->h.disable());
        return http.build();
    }
}
