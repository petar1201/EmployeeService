package com.example.EmployeeService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * This class represents the Security configuration for the web application. It defines the UserDetailsService, PasswordEncoder,
 * SecurityFilterChain, and AuthenticationProvider beans used to secure the application.
 * It also provides a filter chain to restrict access to certain endpoints and requires authentication for certain requests.
 * Basic Authentication is used
 * @author petar
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    /**
     * Returns a UserDetailsService bean that retrieves the user's details from the database using the MyUserDetailsService implementation.
     * @return a UserDetailsService bean that retrieves the user's details from the database.
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }

    /**
     * Returns a PasswordEncoder bean that is used to encode and decode the user's password.
     * @return a PasswordEncoder bean that is used to encode and decode the user's password.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Returns a SecurityFilterChain bean that restricts access to certain endpoints and requires authentication for certain requests.
     * @param http the HttpSecurity object used to define the security configuration for the web application.
     * @return a SecurityFilterChain bean that restricts access to certain endpoints and requires authentication for certain requests.
     * @throws Exception if an error occurs while configuring the HttpSecurity object.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
           return  http.csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/api/employee/**").authenticated()
                    .and().httpBasic().and().build();
    }


    /**
     * Returns an AuthenticationProvider bean that is used to authenticate the user's credentials.
     * @return an AuthenticationProvider bean that is used to authenticate the user's credentials.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }



}