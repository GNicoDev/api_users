package com.users.web.config;

import com.users.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                        HttpMethod.GET,"/api/users/*").permitAll())
                                                                       //"/api/users/*": only we can access to all request GET
                                                                        // that are after users/ in the first level. NO ACCESS to /api/users/listbyemail/nico@gmail.com

                                                                        //"/api/users/**": we can access to all request GET
                                                                      // that are after users/ . ACCESS to /api/users/listbyemail/nico@gmail.com
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.GET,"/api/users/**").hasAnyRole("ADMIN","USER"))
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.POST,"/api/users/**").hasRole("ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.PUT,"/api/users/**").hasRole("ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.DELETE,"/api/users/**").hasRole("ADMIN"))
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
