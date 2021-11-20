package br.edu.ufersa.leon.leon.dtos.schedule;

import br.edu.ufersa.leon.leon.entities.Interval;
import lombok.Data;

@Data
public class LectureDTO {
    private int day;
    private String classe;
    private String location;
    private TimeDTO time;

    static LectureDTO fromInterval(Interval interval) {
        var dto = new LectureDTO();
        dto.setDay(interval.getDate().getDayOfMonth());
        dto.setClasse(interval.getClasse().getModality().getName());
        dto.setLocation(interval.getClasse().getGym().getAddress());
        dto.setTime(TimeDTO.fromInterval(interval));
        return dto;
    }
}
