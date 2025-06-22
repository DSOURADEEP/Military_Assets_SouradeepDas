package com.example.militaryassets.security;

import com.example.militaryassets.entity.User;
import com.example.militaryassets.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepo;

    public SecurityConfig(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/api/assets/**").hasAnyRole("ADMIN", "LOGISTICS", "COMMANDER")
                        .requestMatchers("/api/purchases/**").hasAnyRole("ADMIN", "LOGISTICS","COMMANDER")
                        .requestMatchers("/api/transfers/**").hasAnyRole("ADMIN", "COMMANDER", "LOGISTICS")
                        .anyRequest().authenticated()
                )
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User u = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            return new org.springframework.security.core.userdetails.User(
                    u.getUsername(),
                    u.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + u.getRole()))
            );
        };
    }
}


