package com.example.EmployeeService.pojos;

import java.util.HashMap;

public class VacationDaysInfo {
    private String email;
    HashMap<Integer, Days> daysPerYear;

    public VacationDaysInfo() {
    }

    public VacationDaysInfo(String email) {
        this.email = email;
    }

    public VacationDaysInfo(HashMap<Integer, Days> daysPerYear) {
        this.daysPerYear = daysPerYear;
    }

    public VacationDaysInfo(String email, HashMap<Integer, Days> daysPerYear) {
        this.email = email;
        this.daysPerYear = daysPerYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<Integer, Days> getDaysPerYear() {
        return daysPerYear;
    }

    public void setDaysPerYear(HashMap<Integer, Days> daysPerYear) {
        this.daysPerYear = daysPerYear;
    }
}
