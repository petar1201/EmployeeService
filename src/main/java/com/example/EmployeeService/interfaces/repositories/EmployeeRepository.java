package com.example.EmployeeService.interfaces.repositories;

import com.example.EmployeeService.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The EmployeeRepository interface extends the JpaRepository interface, and provides
 * CRUD operations for the Employee entity.
 * @author petar
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
