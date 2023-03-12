package com.example.EmployeeService.authentication;

import com.example.EmployeeService.entities.Employee;
import com.example.EmployeeService.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private EmployeeService employeeService;


    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        List<UserDetails> userDetailsList = new ArrayList<>();
        List<Employee> all = employeeService.findAll();
        for(Employee employee: all){
            UserDetails user = User.builder()
                    .username(employee.getEmail())
                    .password("{noop}"+employee.getPassword())
                    .roles("Employee")
                    .build();
            userDetailsList.add(user);
        }
        System.out.println("ovde");
        return new InMemoryUserDetailsManager(userDetailsList);
    }


   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
                .csrf().disable()
                .cors().and().authorizeHttpRequests((authz)->authz
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
   }
}