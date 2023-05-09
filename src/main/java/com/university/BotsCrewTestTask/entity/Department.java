package com.university.BotsCrewTestTask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Data
@ToString
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String headOfDepartment;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Lector> lectors = new ArrayList<>();

    public Department() {

    }
}
