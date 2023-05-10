package com.university.BotsCrewTestTask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@ToString
@NoArgsConstructor
@Getter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "head_of_department")
    private String headOfDepartment;

    @ManyToMany(mappedBy = "departments")
    private List<Lector> lectors = new ArrayList<>();
}
