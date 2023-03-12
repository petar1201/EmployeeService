package com.example.EmployeeService.pojos;

import java.sql.Date;

public class UsedVacationDaysInfo {
    String email;
    Date startDate;
    Date endDate;

    public UsedVacationDaysInfo(String email, Date startDate, Date endDate) {
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UsedVacationDaysInfo() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
