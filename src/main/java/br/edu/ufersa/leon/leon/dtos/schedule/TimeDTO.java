package br.edu.ufersa.leon.leon.dtos.schedule;

import br.edu.ufersa.leon.leon.entities.Interval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDTO {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    private String begin;
    private String end;

    public static TimeDTO fromInterval(Interval interval) {
        var dto = new TimeDTO();
        dto.setBegin(interval.getInitialTime().format(formatter));
        dto.setEnd(interval.getFinalTime().format(formatter));
        return dto;
    }
}
