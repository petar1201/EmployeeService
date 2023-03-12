package com.example.EmployeeService.interfaces.service;

import com.example.EmployeeService.pojos.UsedVacationDaysInfo;

import java.sql.Date;
import java.util.List;

public interface UsedVacationsInterface {

    public void addUsedDaysPerYearPerEmployee(String path);

    public void addSingleRow(String emaill, int year1, int month1, int day1
                                         , int year2, int month2, int day2);

    public long calcDays(Date startDate, Date endDate);

    public Date createDate(int year, int month, int day);

    public List<UsedVacationDaysInfo> getUsedDaysInfoFromDate(String email, Date startDate, Date endDate);

}
