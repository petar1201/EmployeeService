package com.example.EmployeeService.interfaces.repositories;

import com.example.EmployeeService.entities.Vacations;
import com.example.EmployeeService.entities.VacationsPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationsRepository extends JpaRepository<Vacations, VacationsPK> {
    List<Vacations> findByVacationsPKYearIsAndVacationsPKEmailIs(int year, String email);
}
