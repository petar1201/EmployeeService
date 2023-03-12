package com.example.EmployeeService.interfaces.service;

import com.example.EmployeeService.entities.Employee;

import java.util.List;

/**
 * The EmployeeInterface provides methods for adding, removing, and managing employee profiles.
 * @author petar
 */
public interface EmployeeInterface {

    /**
     * Adds a single employee profile with the given email and password to the system.
     *
     * @param email the email address of the employee
     * @param password the password for the employee's account
     */
    public void addSingleEmployee(String email, String password);

    /**
     * Removes the employee profile with the given email address from the system.
     *
     * @param email the email address of the employee to remove
     */
    public void removeEmployee(String email);

    /**
     * Returns the list of all employees
     *
     * @return list of all employees
     */
    public List<Employee> findAll();

}
