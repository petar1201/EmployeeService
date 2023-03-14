package com.example.EmployeeService.service;

import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.entity.Vacations;
import com.example.EmployeeService.entity.VacationsPK;
import com.example.EmployeeService.interfaces.repository.EmployeeRepository;
import com.example.EmployeeService.interfaces.repository.VacationsRepository;
import com.example.EmployeeService.interfaces.service.VacationsInterface;
import com.example.EmployeeService.pojo.VacationDaysInfo;
import com.example.EmployeeService.pojo.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * The VacationsService class implements the VacationsInterface and provides methods for managing vacation days for employees.
 * @author petar
 */
@Service
public class VacationsService implements VacationsInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VacationsRepository vacationsRepository;


    /**
     * This method adds a single row of vacation data for an employee in a specific year.
     * If the vacation data already exists for the employee in the given year, the total days are updated.
     * @param year the year of the vacation data.
     * @param email the email of the employee.
     * @param days the number of vacation days.
     * @throws IllegalStateException if the employee does not exist.
     */
    @Override
    public void addSingleRow(int year, String email, int days) {
        Optional<Employee> employee = employeeRepository.findById(email);
        if(employee.isPresent()){
            if(vacationsRepository.findByVacationsPKYearIsAndVacationsPKEmailIs(year, email).size()>0){
                Vacations vacations = vacationsRepository.findByVacationsPKYearIsAndVacationsPKEmailIs(year, email).get(0);
                vacations.setTotalDays(vacations.getTotalDays()+days);
                vacations.setUsedDays(vacations.getUsedDays()+days);
                vacationsRepository.saveAndFlush(vacations);
                return;
            }
            Vacations vacations = new Vacations();
            VacationsPK vacationsPK = new VacationsPK();
            vacationsPK.setYear(year);
            vacationsPK.setEmail(email);
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

    /**
     * This method changes the used vacation days for an employee in a specific year.
     * If the vacation data does not exist for the employee in the given year, a new row is added.
     * @param emaill the email of the employee.
     * @param year the year of the vacation data.
     * @param days the number of used vacation days to be added.
     * @throws IllegalStateException if the employee does not exist.
     */
    @Override
    public void changeUsedDaysForYear(String emaill, int year, int days) {
        String email = emaill;
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
                                vacations1 = vacationsRepository.findByVacationsPKYearIsAndVacationsPKEmailIs((int)i, email).get(0);
                                if(i!=curYr)vacations1.setUsedDays(vacations1.getTotalDays());
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
        else{
            throw new IllegalStateException("Employee doesnt exist, error while adding new record");
        }
    }

    /**
     *
     * Returns information about vacation days that employee has each year
     * @param email The email of the employee.
     * @return return VacationDaysInfo object as information about vacation days
     *
     * */
    @Override
    public VacationDaysInfo getDaysPerYear(String email) {
        VacationDaysInfo vacationDaysInfo = new VacationDaysInfo();
        vacationDaysInfo.setEmail(email);
        List<Vacations> all = vacationsRepository.findByVacationsPKEmailIs(email);
        HashMap<Integer, Days> mapa = new HashMap<>();
        for(Vacations v: all){
            int year = (int) v.getVacationsPK().getYear();
            int totalDays = (int) v.getTotalDays();
            int usedDays = (int) v.getUsedDays();
            int availableDays = totalDays-usedDays;
            Days days = new Days();
            days.setUsedDays(usedDays);
            days.setTotalDays(totalDays);
            days.setAvailableDays(availableDays);
            mapa.put(year, days);
        }
        vacationDaysInfo.setDaysPerYear(mapa);
        return vacationDaysInfo;
    }
}
