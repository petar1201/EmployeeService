package com.example.EmployeeService.services;

import com.example.EmployeeService.entities.Employee;
import com.example.EmployeeService.entities.UsedVacations;
import com.example.EmployeeService.generators.ShaEncryptionGenerator;
import com.example.EmployeeService.interfaces.repositories.EmployeeRepository;
import com.example.EmployeeService.interfaces.repositories.UsedVacationsRepository;
import com.example.EmployeeService.interfaces.service.UsedVacationsInterface;
import com.example.EmployeeService.pojos.UsedVacationDaysInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * UsedVacationsService class implements UsedVacationsInterface and provides
 * methods for managing used vacation days.
 */

@Service
public class UsedVacationsService implements UsedVacationsInterface{

    @Autowired
    private UsedVacationsRepository usedVacationsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VacationsService vacationsService;


    /**
     * Adds a single row for an employee's used vacation days to the database and updates their available days accordingly.
     * The start and end dates are used to calculate the number of business days between them, taking into account weekends and holidays.
     * @param emaill The email address of the employee.
     * @param year1 The year of the start date.
     * @param month1 The month of the start date.
     * @param day1 The day of the start date.
     * @param year2 The year of the end date.
     * @param month2 The month of the end date.
     * @param day2 The day of the end date.
     * @throws IllegalStateException If the start date is after the end date.
     */
    @Override
    public void addSingleRow(String emaill, int year1, int month1, int day1, int year2, int month2, int day2) {
        String email = emaill;
        Optional<Employee> employee = employeeRepository.findById(email);
        if(employee.isPresent()){
            Date startDate = createDate(year1, month1, day1);
            Date endDate = createDate(year2, month2, day2);
            if(startDate.compareTo(endDate)>0){
                throw new IllegalStateException("Start date is after End Date");
            }
            else{
                try {
                    if (year1 == year2) {
                        vacationsService.changeUsedDaysForYear(emaill, year1, (int) calcDays(startDate, endDate));
                    } else {
                        vacationsService.changeUsedDaysForYear(emaill, year1, (int) calcDays(startDate, createDate(year1, 12, 31)));
                        vacationsService.changeUsedDaysForYear(emaill, year2, (int) calcDays(createDate(year2, 1, 1), endDate));
                    }
                    UsedVacations usedVacations = new UsedVacations();
                    //usedVacations.setIdUsed(UsedVacations.id++);
                    usedVacations.setEmail(employee.get());
                    usedVacations.setStartDate(startDate);
                    usedVacations.setEndDate(endDate);
                    usedVacationsRepository.saveAndFlush(usedVacations);
                }
                catch(IllegalStateException e){
                    throw e;
                }
            }

        }
    }

    /**
     * Checks if given date is holiday day or not
     *
     * @param date the date to be checked
     * @return the boolean value representing if given date is holiday or not
     * */
    private boolean isHoliday(LocalDate date){
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        if(month == 1){
            if(day==1 || day==2 || day == 3 || day == 7)return true;
        }
        else if(month == 2){
            if(day == 15 || day ==16)return true;
        }
        else if(month == 5){
            if(day == 1 || day == 2)return true;
        }
        else if(month == 11){
            if(day == 11) return true;
        }
        return false;
    }

    /**
     * Calculates the number of days between the given start and end dates.
     *
     * @param startDate the start date
     * @param endDate the end date
     * @return the number of days between the start and end dates
     */
    @Override
    public long calcDays(Date startDate, Date endDate) {
        LocalDate starttDate = startDate.toLocalDate();
        LocalDate enddDate = endDate.toLocalDate();
        long daysBetween = ChronoUnit.DAYS.between(starttDate, enddDate)+1;
        long businessDays = 0;
        for (int i = 0; i < daysBetween; i++) {
            LocalDate date = starttDate.plusDays(i);
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                if(!isHoliday(date)) businessDays++;
            }
        }
        return businessDays;
    }

    /**
     * Creates a new Date object with the given year, month, and day.
     *
     * @param year the year of the date
     * @param month the month of the date (1-12)
     * @param day the day of the date
     * @return the new Date object
     */
    @Override
    public Date createDate(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        long millis = date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        Date datum = new Date(millis);
        return datum;
    }

    /**
     * Returns a list of UsedVacationsDaysInfo between two dates
     *
     * @param startDate start date
     * @param endDate end date
     * @param email email of the employee
     * @return list of UsedVacationDaysInfo for employee with given email
     */
    @Override
    public List<UsedVacationDaysInfo> getUsedDaysInfoFromDate(String email, Date startDate, Date endDate) {
        List<UsedVacations> all = usedVacationsRepository.findAll();

        List<UsedVacationDaysInfo> usedVacationDaysInfos = new ArrayList<>();

        for(UsedVacations uv: all){
            if(uv.getEmail().getEmail().equals(email)){
                if((startDate.before(uv.getStartDate())||uv.getStartDate().compareTo(startDate)==0)
                        && (endDate.after(uv.getEndDate())||uv.getEndDate().compareTo(endDate)==0) ){
                    UsedVacationDaysInfo usedVacationDaysInfo = new UsedVacationDaysInfo();
                    usedVacationDaysInfo.setEmail(email);
                    usedVacationDaysInfo.setStartDate(uv.getStartDate());
                    usedVacationDaysInfo.setEndDate(uv.getEndDate());
                    usedVacationDaysInfos.add(usedVacationDaysInfo);
                }
            }
        }

       return usedVacationDaysInfos;
    }

}
