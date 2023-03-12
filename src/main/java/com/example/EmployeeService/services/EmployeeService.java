package com.example.EmployeeService.services;


import com.example.EmployeeService.authentication.SecurityConfig;
import com.example.EmployeeService.entities.Employee;
import com.example.EmployeeService.generators.ShaEncryptionGenerator;
import com.example.EmployeeService.interfaces.repositories.EmployeeRepository;
import com.example.EmployeeService.interfaces.service.EmployeeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


/**
 * EmployeeService class implements EmployeeInterface and provides
 * methods for adding and removing employee profiles.
 */
@Service
public class EmployeeService implements EmployeeInterface{

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Adds a single employee profile to the database.
     * @param email the email of the employee.
     * @param password the password of the employee.
     */
    @Override
    public void addSingleEmployee(String email, String password) {
        Employee employee = new Employee(email, password);
        employee.setActive(true);
        employeeRepository.saveAndFlush(employee);
    }

    /**
     * Removes an employee profile from the database.
     * @param email the email of the employee to be removed.
     * @throws IllegalStateException if the employee does not exist.
     */
    @Override
    public void removeEmployee(String email) {
        Optional<Employee> employee = employeeRepository.findById(email);
        if(employee.isPresent()) {
            if(employee.get().isActive()) {
                employee.get().setActive(false);
                employeeRepository.saveAndFlush(employee.get());
            }
        }
        else{
            throw new IllegalStateException("Unexpected ERROR trying to delete self and Employee doesnt exist");
        }
    }
    /**
     * Returns the list of all employees
     *
     * @return list of all employees
     */
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

}
