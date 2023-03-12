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

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path="api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private VacationsService vacationsService;

    @Autowired
    private UsedVacationsService usedVacationsService;

    @PostMapping(path="/usedVacations/new")
    @ResponseBody
    public String addUsedVacationForEmployee(Authentication authentication,
            @RequestParam(name = "startDate", required = true)String startDate,
            @RequestParam(name = "endDate", required = true)String endDate
    ){
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();

        String[] start = startDate.split(" ");

        String[] end = startDate.split(" ");
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


    @GetMapping(path="availableVacations")
    @ResponseBody
    public VacationDaysInfo getDaysPerYear(Authentication authentication){
        String email=((UserDetails) authentication.getPrincipal()).getUsername();
        return vacationsService.getDaysPerYear(email);
    }

    @GetMapping(path="usedVacations")
    @ResponseBody
    public List<UsedVacationDaysInfo> getUsedDaysForDates(Authentication authentication,
                                                          @RequestParam(name = "startDate", required = true)String startDate,
                                                          @RequestParam(name = "endDate", required = true)String endDate
    ){
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();


        String[] start = startDate.split(" ");

        String[] end = startDate.split(" ");
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
