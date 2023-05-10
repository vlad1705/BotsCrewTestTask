package com.university.BotsCrewTestTask.dto;

import lombok.Data;

@Data
public class DegreeStatistic {
    private String degree;
    private Long count;

    public DegreeStatistic(String degree, Long count) {
        this.degree = degree;
        this.count = count;
    }
}
