package com.users.web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private CorsConfig corsConfig;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/api/auth/**").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.GET,"/api/users/*").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.GET,"/api/users/**").hasAnyRole("ADMIN","USER"))
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.POST,"/api/users/**").hasRole("ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.PUT,"/api/users/**").hasRole("ADMIN"))
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
