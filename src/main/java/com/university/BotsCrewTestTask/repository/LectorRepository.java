package com.university.BotsCrewTestTask.repository;

import com.university.BotsCrewTestTask.dto.DegreeStatistic;
import com.university.BotsCrewTestTask.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query("SELECT new com.university.BotsCrewTestTask.dto.DegreeStatistic(l.degree, COUNT(*)) " +
            "FROM Lector l " +
            "JOIN l.departments d " +
            "WHERE d.name = :departmentName " +
            "GROUP BY l.degree")
    List<DegreeStatistic> getSumOfDifferentDegreesWithinDepartment(String departmentName);


    @Query("SELECT AVG(l.salary) " +
            "FROM Lector l" +
            " JOIN l.departments d" +
            " WHERE d.name = :departmentName")
    Optional<Double> findAverageSalaryForDepartment(String departmentName);

    @Query("SELECT l FROM Lector l WHERE CONCAT(l.firstName, ' ', l.lastName) LIKE %:template%")
    List<Lector> findLectorsByTemplate(String template);
}
