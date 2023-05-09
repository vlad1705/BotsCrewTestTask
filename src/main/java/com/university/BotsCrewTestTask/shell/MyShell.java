package com.university.BotsCrewTestTask.shell;

import com.university.BotsCrewTestTask.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShellComponent
@RequiredArgsConstructor
public class MyShell {
    private final DepartmentService departmentService;
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(\\w+) statistics$", Pattern.CASE_INSENSITIVE);

    @ShellMethod(key = "Who is head of department")
    public String getHeadOfDepartment(String departmentName) {
            return departmentService.getHeadOfDepartment(departmentName);
    }

    // this method may seem to be complicated but such solution was created to match the exact requirements specified in task file
    // the problem is that we cannot dynamically pass the value inside "key" argument so i created this solution to bypass restrictions
    @ShellMethod(key = "Show")
    public String showStatistics(String departmentName, String statisticsWord) {
        Matcher matcher = COMMAND_PATTERN.matcher(departmentName + " " + statisticsWord);
        if (matcher.matches()) {
            String departmentNameMatch = matcher.group(1);
            return departmentService.getDepartmentNameStatistic(departmentNameMatch);
        } else {
            throw new RuntimeException("Invalid input");
        }
    }

    @ShellMethod(key = "Show the average salary for the department ")
    public String getAverageSalaryForTheDepartment(String departmentName) {
        return "The average salary of " + departmentName + " is " + departmentService.getAverageSalaryForTheDepartment(departmentName);
    }


    @ShellMethod(key = "Show count of employee for ")
    public String getCountOfEmployeesByDepartment(String departmentName) {
        return departmentService.getCountOfEmployeesByDepartment(departmentName);
    }

    @ShellMethod(key = "Global search by ")
    public String getLectorNameByTemplate(String template) {
        return departmentService.getLectorsByTemplate(template);
    }


}