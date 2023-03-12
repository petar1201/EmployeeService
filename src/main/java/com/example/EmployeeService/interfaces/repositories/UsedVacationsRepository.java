package com.example.EmployeeService.interfaces.repositories;

import com.example.EmployeeService.entities.UsedVacations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsedVacationsRepository extends JpaRepository<UsedVacations, String> {
}
