package br.edu.ufersa.leon.leon.dtos.schedule;

import br.edu.ufersa.leon.leon.entities.Interval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleReportDTO {
    private int month;
    private List<LectureDTO> lectures;

    public static ScheduleReportDTO from(int month, List<Interval> intervals) {
        var dto = new ScheduleReportDTO();
        dto.setMonth(month);
        var lectures = intervals.stream().map(LectureDTO::fromInterval).collect(Collectors.toList());
        dto.setLectures(lectures);
        return dto;
    }
}
