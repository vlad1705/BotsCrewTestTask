package com.university.BotsCrewTestTask.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class DegreeStatistic {
    private String degree;
    private Long count;

    public DegreeStatistic(String degree, Long count) {
        this.degree = degree;
        this.count = count;
    }
}
