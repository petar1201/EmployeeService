package com.example.EmployeeService.interfaces.repositories;

import com.example.EmployeeService.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
