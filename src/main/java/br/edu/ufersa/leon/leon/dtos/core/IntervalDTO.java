package br.edu.ufersa.leon.leon.dtos.core;

import br.edu.ufersa.leon.leon.entities.Interval;
import lombok.Data;

import java.time.LocalTime;

@Data
public class IntervalDTO {
    private Long id;
    private LocalTime initialTime;
    private LocalTime finalTime;

    public static IntervalDTO fromEntity(Interval interval) {
        var dto = new IntervalDTO();
        dto.setId(interval.getId());
        dto.setInitialTime(interval.getInitialTime());
        dto.setFinalTime(interval.getFinalTime());
        return dto;
    }
}