package com.example.springdemo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
            .requestMatchers("/api/v1/books/**").denyAll()
            .requestMatchers("/api/v1/authors/**").denyAll()
            .requestMatchers("/api/v1/auth/**").permitAll()
            .anyRequest().authenticated() 
         ) .formLogin(formLoginCustomizer -> formLoginCustomizer.loginPage("/login").permitAll())
         .logout(logoutCustomizer -> logoutCustomizer.logoutSuccessUrl("/login").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserDetails user =
			 User.builder()
				.username("user")
				.password(encoder.encode("password"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
