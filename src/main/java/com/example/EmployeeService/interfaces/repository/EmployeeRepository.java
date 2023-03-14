package com.example.EmployeeService.interfaces.repository;

import com.example.EmployeeService.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The EmployeeRepository interface extends the JpaRepository interface, and provides
 * CRUD operations for the Employee entity.
 * @author petar
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
