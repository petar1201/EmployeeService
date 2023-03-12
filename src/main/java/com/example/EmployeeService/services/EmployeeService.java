package com.example.EmployeeService.services;


import com.example.EmployeeService.entities.Employee;
import com.example.EmployeeService.generators.ShaEncryptionGenerator;
import com.example.EmployeeService.interfaces.repositories.EmployeeRepository;
import com.example.EmployeeService.interfaces.service.EmployeeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@Service
public class EmployeeService implements EmployeeInterface, UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void addEmployeeProfiles(String path) {

       // String csvFile = "C:\\Users\\petar\\Desktop\\TehnicalTaskRBT\\Technical assignment\\Samples\\employee_profiles.csv";

        try{
            Scanner sc = new Scanner(new File(path));
            sc.useDelimiter("\n");
            while (sc.hasNext())  //returns a boolean value
            {   String line = sc.next();
                String email = line.split(",")[0];
                String password = line.split(",")[1];
                if(email.equals("Employee Email"))continue;
                addSingleEmployee(email, password);
            }
            sc.close();
        } catch (FileNotFoundException e) {
           throw new IllegalStateException("File not found: " + e.getMessage());
        }

    }

    @Override
    public void addSingleEmployee(String email, String password) {
        Employee employee = new Employee(ShaEncryptionGenerator.hashString(email),
                ShaEncryptionGenerator.hashString(password));
        employee.setActive(true);
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void removeEmployee(String email) {
        Optional<Employee> employee = employeeRepository.findById(ShaEncryptionGenerator.hashString(email));
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(ShaEncryptionGenerator.hashString(username));
        if (!employee.isPresent()) {
            throw new UsernameNotFoundException("Employee not found");
        }
        return new User(employee.get().getEmail(), employee.get().getPassword(), new ArrayList<>()) {
            @Override
            public String getUsername() {
                return username;
            }
        };
    }
}
