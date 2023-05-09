package com.university.BotsCrewTestTask.shell;

import com.university.BotsCrewTestTask.repository.DepartmentRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departmentHeadName")
@RequiredArgsConstructor
public class Controller {
    private final DepartmentRepository departmentRepository;

    @GetMapping
    public String returnSomething(@RequestParam String name){
        return departmentRepository.findByName(name).getHeadOfDepartment();
    }
}
