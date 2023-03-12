package com.example.EmployeeService.controllers;


import com.example.EmployeeService.pojos.UsedVacationDaysInfo;
import com.example.EmployeeService.pojos.VacationDaysInfo;
import com.example.EmployeeService.services.EmployeeService;
import com.example.EmployeeService.services.UsedVacationsService;
import com.example.EmployeeService.services.VacationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.List;


/**
 * This class serves as the REST controller for managing employee data, vacation days and used vacation days.
 * It defines the endpoints for handling HTTP requests and responses related to employees and their vacations.
 */
@RestController
@RequestMapping(path="api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private VacationsService vacationsService;

    @Autowired
    private UsedVacationsService usedVacationsService;


    /**
     * Endpoint for adding a used vacation period for a given employee.
     * @param authentication An object representing the authenticated principal.
     * @param startDate A string representing the start date of the used vacation period in "dd mm yyyy" format.
     * @param endDate A string representing the end date of the used vacation period in "dd mm yyyy" format.
     * @return A string indicating that new data for an employee has been loaded.
     * @throws IllegalStateException if either the start or end date format is invalid.
     */
    @PostMapping(path="/usedVacations/new")
    @ResponseBody
    public String addUsedVacationForEmployee(Principal authentication,
                                             @RequestParam(name = "startDate", required = true)String startDate,
                                             @RequestParam(name = "endDate", required = true)String endDate
    ){
        String email = authentication.getName();

        String[] start = startDate.split(" ");

        String[] end = endDate.split(" ");
        if(start.length!=3 || end.length!=3)throw new IllegalStateException("Date should be in dd mm yyyy");
        int year1, month1, day1,year2,month2, day2;
        try {
            year1 = Integer.parseInt(start[2]);
            year2 = Integer.parseInt(end[2]);
            month1 = Integer.parseInt(start[1]);
            month2 = Integer.parseInt(end[1]);
            day1 = Integer.parseInt(start[0]);
            day2 = Integer.parseInt(end[0]);
        }
        catch(NumberFormatException e){
            throw new IllegalStateException("Date should be in dd mm yy where d,m,y are integers only");
        }
        usedVacationsService.addSingleRow(email,year1,month1,day1,year2,month2,day2);
        return "New data for Employee " + email + " loaded";
    }



    /**
     * Endpoint for getting the available vacation days for a given employee.
     * @param authentication An object representing the authenticated principal.
     * @return An object representing the available vacation days for the given employee.
     */
    @GetMapping(path="availableVacations")
    @ResponseBody
    public VacationDaysInfo getDaysPerYear(Principal authentication){
        String email=authentication.getName();
        return vacationsService.getDaysPerYear(email);
    }


    /**
     * Endpoint for getting the used vacation days for a given employee within a specific date range.
     * @param authentication An object representing the authenticated principal.
     * @param startDate A string representing the start date of the period to retrieve used vacation days for, in "dd mm yyyy" format.
     * @param endDate A string representing the end date of the period to retrieve used vacation days for, in "dd mm yyyy" format.
     * @return A list of objects representing the used vacation days for the given employee within the specified date range.
     * @throws IllegalStateException if either the start or end date format is invalid.
     */
    @GetMapping(path="usedVacations/get")
    @ResponseBody
    public List<UsedVacationDaysInfo> getUsedDaysForDates(Principal authentication,
                                                          @RequestParam(name = "startDate", required = true)String startDate,
                                                          @RequestParam(name = "endDate", required = true)String endDate
    ){
        String email = authentication.getName();


        String[] start = startDate.split(" ");

        String[] end = endDate.split(" ");
        if(start.length!=3 || end.length!=3)throw new IllegalStateException("Date should be in dd mm yyyy");
        int year1, month1, day1,year2,month2, day2;
        try {
            year1 = Integer.parseInt(start[2]);
            year2 = Integer.parseInt(end[2]);
            month1 = Integer.parseInt(start[1]);
            month2 = Integer.parseInt(end[1]);
            day1 = Integer.parseInt(start[0]);
            day2 = Integer.parseInt(end[0]);
        }
        catch(NumberFormatException e){
            throw new IllegalStateException("Date should be in dd mm yy where d,m,y are integers only");
        }

        Date startD = usedVacationsService.createDate(year1, month1, day1);
        Date endD = usedVacationsService.createDate(year2, month2, day2);

        return usedVacationsService.getUsedDaysInfoFromDate(email,startD, endD);
    }

}
