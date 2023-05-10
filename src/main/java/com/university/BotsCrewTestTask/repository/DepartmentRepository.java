package com.university.BotsCrewTestTask.repository;

import com.university.BotsCrewTestTask.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String departmentName);

    //initially tried to use method name query but by some reason it worked in wrong way
    //also i have doubts where this method should be placed better
    @Query("SELECT COUNT(l)" +
            "FROM Lector l " +
            "JOIN l.departments department " +
            "WHERE department.name = :departmentName")
    Long countByLectorsDepartmentsName(String departmentName);
}

