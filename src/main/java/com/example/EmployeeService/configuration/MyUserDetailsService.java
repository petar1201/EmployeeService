package com.example.EmployeeService.configuration;

import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.interfaces.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * This class represents a UserDetailsService implementation that retrieves the user's details from the database using the EmployeeRepository.
 * It provides a method to load the user by username and return a UserDetails object containing the user's details.
 * It is used for BasicAuthorization
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * Loads the user by username and returns a UserDetails object containing the user's details.
     * @param username the username of the user to be loaded.
     * @return a UserDetails object containing the user's details.
     * @throws UsernameNotFoundException if the user with the given username is not found in the database.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Employee> byId = employeeRepository.findById(username);

        return byId.map(MyUserDetails::new).orElseThrow(()->
                new UsernameNotFoundException("User not found with userame: " + username));

    }
}