package com.adobe.securitydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    DataSource dataSource; // pool of DB connections

    @Autowired
    public  void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().dataSource(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    // Authorization
    @Bean
    public SecurityFilterChain configure(HttpSecurity security) throws  Exception {
        security.authorizeHttpRequests( requests -> requests.requestMatchers("/customer")
                .hasAnyRole("CUSTOMER", "ADMIN")
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/").permitAll())
                .formLogin(Customizer.withDefaults());
            return security.build();
    }

    // Authentication
    // {noop} --> No Password Encoder ==> plain
    // {bcrpyt} --> password is Bcrypt encoded
//    @Bean
//    public InMemoryUserDetailsManager users() {
//        return  new InMemoryUserDetailsManager(
//                User.withUsername("roger")
//                        .password("{noop}secret123")
//                        .authorities("ROLE_CUSTOMER").build(),
//                User.withUsername("rita")
//                        .password("{noop}secret123")
//                        .authorities("ROLE_ADMIN", "ROLE_CUSTOMER")
//                        .build());
//
//    }
}
