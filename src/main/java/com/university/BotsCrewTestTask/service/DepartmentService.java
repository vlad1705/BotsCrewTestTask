package com.university.BotsCrewTestTask.service;

import com.university.BotsCrewTestTask.dto.DegreeStatistic;
import com.university.BotsCrewTestTask.entity.Department;
import com.university.BotsCrewTestTask.repository.DepartmentRepository;
import com.university.BotsCrewTestTask.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;


    public String getHeadOfDepartment(String departmentName) {
        return departmentRepository.findByName(departmentName)
                .map(Department::getHeadOfDepartment)
                .map(head -> "Head of " + departmentName + " is " + head)
                .orElseThrow(() -> new NoSuchElementException("Department " + departmentName + " does not exist! "));
    }


    public String getDepartmentNameStatistic(String departmentName) {
        List<DegreeStatistic> degreeStatistic = lectorRepository.getSumOfDifferentDegreesWithinDepartment(departmentName);

        if (!degreeStatistic.isEmpty()){
            return degreeStatistic.stream()
                    .map(ds -> ds.getDegree() + "s - " + ds.getCount())
                    .collect(Collectors.joining("\n"));
        } else {
            throw new NoSuchElementException("Department " + departmentName + " does not exist or no lectors for this department were found! ");
        }

    }

    public Double getAverageSalaryForTheDepartment(String departmentName) {
        return lectorRepository.findAverageSalaryForDepartment(departmentName)
                .orElseThrow(() -> new NoSuchElementException("Department " + departmentName + " does not exist or no lectors salary for this department were found! "));
    };


    public String getCountOfEmployeesByDepartment(String departmentName) {
        // need to do extra query to check whether such department exists
        return departmentRepository.findByName(departmentName)
                .map(count->departmentRepository.countByLectorsDepartmentsName(departmentName).toString())
                .orElseThrow(() -> new NoSuchElementException("Department " + departmentName + " does not exist! "));
    }


    public String getLectorsByTemplate(String template) {
        return lectorRepository.findLectorsByTemplate(template).stream()
                .map(lector -> lector.getFirstName() + " " + lector.getLastName())
                .collect(Collectors.joining(", "));
    }

};
