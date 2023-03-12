package com.example.EmployeeService.interfaces.repositories;

import com.example.EmployeeService.entities.UsedVacations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The UsedVacationsRepository interface extends the JpaRepository interface, and provides
 * CRUD operations for the UsedVacations entity.
 * @author petar
 */
public interface UsedVacationsRepository extends JpaRepository<UsedVacations, String> {
}
