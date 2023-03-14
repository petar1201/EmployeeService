package com.example.EmployeeService.interfaces.service;

import com.example.EmployeeService.pojo.VacationDaysInfo;

/**
 * This interface provides methods to manage employee vacations.
 * @author petar
 */
public interface VacationsInterface {

    /**
     * Adds a single row to the table that maps employees to the number of vacation days they are entitled to per year.
     * @param year The year the entitlement is for.
     * @param email The email of the employee.
     * @param days The number of days the employee is entitled to.
     */
    public void addSingleRow(int year, String email, int days);

    /**
     * Changes the number of vacation days an employee has used in a given year.
     * @param emaill The email of the employee.
     * @param year The year the entitlement is for.
     * @param days The new number of days used.
     */
    public void changeUsedDaysForYear(String emaill, int year, int days);

    /**
     *
     * Returns information about vacation days that employee has each year
     * @param email The email of the employee.
     * @return return VacationDaysInfo object as information about vacation days
     * */
    public VacationDaysInfo getDaysPerYear(String email);

}
