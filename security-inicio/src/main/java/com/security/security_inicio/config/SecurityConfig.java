package com.security.security_inicio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//Import esta libreria
import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

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
        http.formLogin(withDefaults());
        //http.formLogin(f->f.disable());
        http.httpBasic(withDefaults());
        //http.httpBasic(h->h.disable());
        return http.build();
    }

    //@Bean
    //public UserDetailsService userDetailsService() {
    //    //{noop} contraseña no está codificada (es decir, se almacena en texto plano)
    //    // para entornos de desarrollo o pruebas
    //    UserDetails user = User.withUsername("user").password("{noop}12345").authorities("read").build();
    //    //UserDetails admin = User.withUsername("admin").password("{noop}54321").authorities("admin").build();
    //    //UserDetails user = User.withUsername("user").password("{noop}EazyBytes@12345").authorities("read").build();
    //    UserDetails admin = User.withUsername("admin")
    //                        .password("{bcrypt}$2y$10$9EFb.4nq8tMdjfvf2HuVCOAjfNa2ywOxXzEMQON5txoFauv52vpKy")
    //                        .authorities("admin").build();
    //    return new InMemoryUserDetailsManager(user, admin);
    //}

    //@Bean
    //public UserDetailsService userDetailsService(DataSource dataSource) {
    //    return new JdbcUserDetailsManager(dataSource);
    //}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
