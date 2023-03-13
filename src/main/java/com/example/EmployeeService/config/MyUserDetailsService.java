package com.example.EmployeeService.config;

import com.example.EmployeeService.entities.Employee;
import com.example.EmployeeService.interfaces.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Employee> byId = employeeRepository.findById(username);

        return byId.map(MyUserDetails::new).orElseThrow(()->
                new UsernameNotFoundException("User not found with userame: " + username));

    }
}