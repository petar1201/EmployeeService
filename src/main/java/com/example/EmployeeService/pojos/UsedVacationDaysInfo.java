package com.example.EmployeeService.pojos;

import java.sql.Date;

/**
 * A plain old Java object (POJO) that represents vacation record for an employee.
 * This object is used to return a value from an HTTP request.
 */
public class UsedVacationDaysInfo {
    String email;
    Date startDate;
    Date endDate;

    /**
     * Constructs an instance of {@code UsedVacationDaysInfo} with the specified email, start date, and end date.
     * @param email The email address of the employee who used the vacation days.
     * @param startDate The start date of the vacation.
     * @param endDate The end date of the vacation.
     * */
    public UsedVacationDaysInfo(String email, Date startDate, Date endDate) {
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an instance of {@code UsedVacationDaysInfo} with no parameters.
     */
    public UsedVacationDaysInfo() {
    }

    /**
     * Returns the email address of the employee.
     * @return The email address of the employee who used the vacation days.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the employee.
     * @param email The email address of the employee who used the vacation days.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the start date of the vacation.
     * @return The start date of the vacation.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the vacation.
     * @param startDate The start date of the vacation.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the vacation.
     * @return The end date of the vacation.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the vacation.
     * @param endDate The end date of the vacation.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
