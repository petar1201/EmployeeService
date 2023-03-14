package com.example.EmployeeService.pojo;

/**
 * The Days class represents a Plain Old Java Object (POJO) that contains information about an employee's vacation days,
 * including the total number of days, used days, and available days.
 */
public class Days {

    private int totalDays;
    private int usedDays;
    private int availableDays;


    /**
     * Constructs a Days object with the given total number of days, used days, and available days.
     * @param totalDays The total number of vacation days for an employee.
     * @param usedDays The number of vacation days used by an employee.
     * @param availableDays The number of vacation days available to an employee.
     */
    public Days(int totalDays, int usedDays, int availableDays) {
        this.totalDays = totalDays;
        this.usedDays = usedDays;
        this.availableDays = availableDays;
    }


    /**
     * Constructs an empty Days object with default values of 0 for all fields.
     */
    public Days() {
    }

    /**
     * Returns the total number of vacation days for an employee.
     * @return The total number of vacation days.
     */
    public int getTotalDays() {
        return totalDays;
    }

    /**
     * Sets the total number of vacation days for an employee.
     * @param totalDays The total number of vacation days.
     */
    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    /**
     * Returns the number of vacation days used by an employee.
     * @return The number of vacation days used.
     */
    public int getUsedDays() {
        return usedDays;
    }

    /**
     * Sets the number of vacation days used by an employee.
     * @param usedDays The number of vacation days used.
     */
    public void setUsedDays(int usedDays) {
        this.usedDays = usedDays;
    }

    /**
     * Returns the number of vacation days available to an employee.
     * @return The number of vacation days available.
     */
    public int getAvailableDays() {
        return availableDays;
    }


    /**
     * Sets the number of vacation days available to an employee.
     * @param availableDays The number of vacation days available.
     */
    public void setAvailableDays(int availableDays) {
        this.availableDays = availableDays;
    }
}
