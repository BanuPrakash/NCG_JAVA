package com.adobe.securitydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Authorization
    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws  Exception {
        httpSecurity.authorizeHttpRequests( requests -> requests.requestMatchers("/admin")
                .hasRole("ADMIN")
                .requestMatchers("/hello").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers("/").permitAll())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("rita")
                        .password("{noop}secret")
                        .authorities("ROLE_ADMIN").build(),
                User.withUsername("roger")
                        .password("{noop}secret")
                        .authorities("ROLE_CUSTOMER").build()
        );
    }
}
