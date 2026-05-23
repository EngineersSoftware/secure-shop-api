package com.devseniorcode.andresh.secure_shop_api.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(Customizer.withDefaults())
        .authorizeHttpRequests(auth -> auth.requestMatchers("/public/**").permitAll()
                                           .requestMatchers(HttpMethod.GET, "/api/products/**").authenticated()
                                           .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
                                           .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
                                           .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
                                           .anyRequest().authenticated()
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails client = User.builder()
        .username("cliente@shop.com")
        .password(passwordEncoder.encode("12345"))
        .roles("CLIENTE") // ROLE_ -> ROLE_CLIENT
        .build();

        UserDetails admin = User.builder()
        .username("admin@shop.com")
        .password(passwordEncoder.encode("admin123"))
        .roles("ADMIN", "CLIENTE") // ROLE_ -> ROLE_ADMIN
        .build();

        return new InMemoryUserDetailsManager(client, admin);
    }

}
