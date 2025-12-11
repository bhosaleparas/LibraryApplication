package com.example.LibraryApplication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.
                authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/api/books/**").authenticated()
                )
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

}
