package com.example.EmployeeService.pojo;

import java.util.HashMap;

/**
 * A plain old Java object (POJO) that represents vacation days information for an employee.
 * This object is used to return a value from an HTTP request.
 */
public class VacationDaysInfo {
    private String email;
    HashMap<Integer, Days> daysPerYear;

    /**
     * Constructs a new instance of VacationDaysInfo with no arguments.
     */
    public VacationDaysInfo() {
    }

    /**
     * Constructs a new instance of VacationDaysInfo with the specified email address.
     * @param email The email address of the employee.
     */
    public VacationDaysInfo(String email) {
        this.email = email;
    }

    /**
     * Constructs a new instance of VacationDaysInfo with the specified vacation days information.
     * @param daysPerYear A HashMap that maps year to vacation days information for that year.
     */
    public VacationDaysInfo(HashMap<Integer, Days> daysPerYear) {
        this.daysPerYear = daysPerYear;
    }

    /**
     * Constructs a new instance of VacationDaysInfo with the specified email address and vacation days information.
     * @param email The email address of the employee.
     * @param daysPerYear A HashMap that maps year to vacation days information for that year.
     */
    public VacationDaysInfo(String email, HashMap<Integer, Days> daysPerYear) {
        this.email = email;
        this.daysPerYear = daysPerYear;
    }

    /**
     * Returns the email address of the employee.
     * @return The email address of the employee.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the employee.
     * @param email The email address of the employee.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the vacation days information for each year in a HashMap.
     * @return A HashMap that maps year to vacation days information for that year.
     */
    public HashMap<Integer, Days> getDaysPerYear() {
        return daysPerYear;
    }

    /**
     * Sets the vacation days information for each year in a HashMap.
     * @param daysPerYear A HashMap that maps year to vacation days information for that year.
     */
    public void setDaysPerYear(HashMap<Integer, Days> daysPerYear) {
        this.daysPerYear = daysPerYear;
    }
}
