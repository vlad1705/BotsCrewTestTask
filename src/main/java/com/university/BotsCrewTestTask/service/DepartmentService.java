package com.university.BotsCrewTestTask.service;

import com.university.BotsCrewTestTask.repository.DepartmentRepository;
import com.university.BotsCrewTestTask.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Transactional(readOnly = true)
    public String getHeadOfDepartment(String departmentName) {
        var department = departmentRepository.findByName(departmentName);
        if(department!=null){
            return "Head of " + departmentName + " is " + department.getHeadOfDepartment();
        }
        else {
            throw new NoSuchElementException("Such department does not exist! ");
        }
    }

    @Transactional(readOnly = true)
    public String getDepartmentNameStatistic(String departmentName) {
        var degreeStatistic = lectorRepository.getSumOfDifferentDegreesWithinDepartment(departmentName);

        if (!degreeStatistic.isEmpty()){
            return degreeStatistic.stream().map(ds -> ds.getDegree() + "s - " + ds.getCount()).collect(Collectors.joining("\n"));
        } else {
            throw new NoSuchElementException("Such department does not exist or no lectors for this department were found! ");
        }

    }

    @Transactional(readOnly = true)
    public Double getAverageSalaryForTheDepartment(String departmentName) {
        var averageSalary = lectorRepository.findAverageSalaryForDepartment(departmentName);
        if(averageSalary!=null){
            return new BigDecimal(averageSalary).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        else {
            throw new NoSuchElementException("Such department does not exist or no lectors salary for this department were found! ");
        }
    }

    @Transactional(readOnly = true)
    public String getCountOfEmployeesByDepartment(String departmentName) {
        // need to do extra query to check whether such department exists
        if(departmentRepository.findByName(departmentName)!=null){
            return lectorRepository.countByDepartmentName(departmentName).toString();
        }else{
            throw new NoSuchElementException("Such department does not exist! ");
        }

    }

    @Transactional(readOnly = true)
    public String getLectorsByTemplate(String template) {
        return lectorRepository.findLectorsByTemplate(template).stream()
                .map(lector -> lector.getFirstName() + " " + lector.getLastName())
                .collect(Collectors.joining(", "));
    }

};
