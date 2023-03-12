package com.example.EmployeeService.services;

import com.example.EmployeeService.entities.Employee;
import com.example.EmployeeService.entities.Vacations;
import com.example.EmployeeService.entities.VacationsPK;
import com.example.EmployeeService.generators.ShaEncryptionGenerator;
import com.example.EmployeeService.interfaces.repositories.EmployeeRepository;
import com.example.EmployeeService.interfaces.repositories.VacationsRepository;
import com.example.EmployeeService.interfaces.service.VacationsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class VacationsService implements VacationsInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VacationsRepository vacationsRepository;

    @Override
    public void addDaysPerYearPerEmployee(String path) {

        int year=-1;

        try{
            Scanner sc = new Scanner(new File(path));
            sc.useDelimiter("\n");
            while (sc.hasNext())  //returns a boolean value
            {   String nextLine = sc.next();
                String email = nextLine.split(",")[0];
                String days = nextLine.split(",")[1];
                if(email.equals("Employee"))continue;
                if(email.equals("Vacation year")){
                    year=Integer.parseInt(""+days.charAt(0)+days.charAt(1)+days.charAt(2)+days.charAt(3));
                    continue;
                }
                if(year == -1)throw new IllegalStateException("Bad header, unknown year");
                addSingleRow(year, email, Integer.parseInt(days.trim().replaceAll("\n$", "")));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File not found: " + e.getMessage());
        }
    }

    @Override
    public void addSingleRow(int year, String email, int days) {
        Optional<Employee> employee = employeeRepository.findById(ShaEncryptionGenerator.hashString(email));
        if(employee.isPresent()){
            Vacations vacations = new Vacations();
            VacationsPK vacationsPK = new VacationsPK();
            vacationsPK.setYear(year);
            vacationsPK.setEmail(ShaEncryptionGenerator.hashString(email));
            vacations.setVacationsPK(vacationsPK);
            vacations.setEmployee(employee.get());
            vacations.setTotalDays(days);
            vacations.setUsedDays(0);
            vacationsRepository.saveAndFlush(vacations);
        }
        else{
            throw new IllegalStateException("Trying to add vacation days for Employee who doesnt exist");
        }
    }

    @Override
    public void changeUsedDaysForYear(String emaill, int year, int days) {
        String email = ShaEncryptionGenerator.hashString(emaill);
        Optional<Employee> employee = employeeRepository.findById(email);
        if(employee.isPresent()){

            List<Vacations> vacations = vacationsRepository.findByVacationsPKYearIsAndVacationsPKEmailIs(year,email);
            for(Vacations v: vacations){
                if(v.getTotalDays()-v.getUsedDays()>=days){
                    v.setUsedDays(v.getUsedDays()+days);
                    vacationsRepository.saveAndFlush(v);
                    break;
                }
                else{
                    int curYr=year-1;
                    long totalDays=0;
                    long usedDays=0;
                    long daysAvailable = v.getTotalDays()-v.getUsedDays();
                    boolean okay = false;
                    while(vacationsRepository.findByVacationsPKYearIsAndVacationsPKEmailIs(curYr,email).size()>0){
                        Vacations vacations1 = vacationsRepository.findByVacationsPKYearIsAndVacationsPKEmailIs(curYr, email).get(0);
                        totalDays = vacations1.getTotalDays();
                        usedDays = vacations1.getUsedDays();
                        if(totalDays-usedDays+daysAvailable>=days){
                            okay=true;
                            for(long i = year;i>=curYr;i--){
                                vacations1 = vacationsRepository.findByVacationsPKYearIsAndVacationsPKEmailIs(curYr, email).get(0);
                                if(year!=curYr)vacations1.setUsedDays(vacations1.getTotalDays());
                                else vacations1.setUsedDays(totalDays-(totalDays-usedDays+daysAvailable-days));
                                vacationsRepository.saveAndFlush(vacations1);
                            }
                            break;
                        }
                        daysAvailable+= totalDays-usedDays;
                        curYr--;
                    }
                    if(!okay){
                        throw new IllegalStateException("Record od new used vacation days cant be added due to inefficient amount of days. Sorry, you need to work:(");
                    }
                }

            }
        }
    }
}
