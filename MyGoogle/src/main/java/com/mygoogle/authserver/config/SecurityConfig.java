package com.mygoogle.authserver.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService uds) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    /*
    @Bean
    public SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {

       http.csrf().disable();

        http.authorizeRequests()
            .antMatchers("/mygoogle/oauth/token", "/mygoogle/register").permitAll()
            .anyRequest().authenticated();

        http.httpBasic();

        return http.build();
    }
    */
    @Bean
    public SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {
    	

        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for Postman testing
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/mygoogle/oauth/token", "/mygoogle/register").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Enables basic auth for token endpoint

        return http.build();
    }
    /*
    @Bean
    public SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .formLogin();
        return http.build();
    }
    */
}
