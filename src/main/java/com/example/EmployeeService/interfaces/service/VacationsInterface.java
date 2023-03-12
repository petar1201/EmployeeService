package com.example.EmployeeService.interfaces.service;

import com.example.EmployeeService.pojos.VacationDaysInfo;

public interface VacationsInterface {

    public void addDaysPerYearPerEmployee(String path);

    public void addSingleRow(int year, String email, int days);

    public void changeUsedDaysForYear(String emaill, int year, int days);

    public VacationDaysInfo getDaysPerYear(String email);



}
