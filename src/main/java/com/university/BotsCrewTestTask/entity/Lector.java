package com.university.BotsCrewTestTask.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "lector")
@Data
@ToString
@AllArgsConstructor
@Builder
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Long salary;

    @Column
    private String degree;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Lector() {

    }
}
