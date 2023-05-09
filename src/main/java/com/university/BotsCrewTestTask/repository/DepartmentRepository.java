package com.university.BotsCrewTestTask.repository;

import com.university.BotsCrewTestTask.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String departmentName);

}

