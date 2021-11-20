package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.schedule.ScheduleReportDTO;
import br.edu.ufersa.leon.leon.entities.Interval;
import br.edu.ufersa.leon.leon.entities.User;
import br.edu.ufersa.leon.leon.repositories.IntervalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final IntervalRepository intervalRepository;

    public ScheduleService(IntervalRepository intervalRepository) {
        this.intervalRepository = intervalRepository;
    }

    public List<ScheduleReportDTO> getScheduleOfUser(User user, int month) {
        var initialDate = LocalDate.now().withMonth(month).withDayOfMonth(1);
        var finalDate = initialDate.withDayOfMonth(initialDate.lengthOfMonth());
        var intervals = intervalRepository.getScheduleBetween(user.getId(), initialDate, finalDate);
        return mapIntervalsToSchedules(intervals);
    }

    public List<ScheduleReportDTO> getScheduleOfUserBetween(User user, int initialMonth, int finalMonth) {
        var initialDate = LocalDate.now().withMonth(initialMonth).withDayOfMonth(1);
        var firstDayAtFinalMonth = initialDate.withMonth(finalMonth);
        var finalDate = firstDayAtFinalMonth.withDayOfMonth(firstDayAtFinalMonth.lengthOfMonth());
        var intervals = intervalRepository.getScheduleBetween(user.getId(), initialDate, finalDate);
        return mapIntervalsToSchedules(intervals);
    }

    private List<ScheduleReportDTO> mapIntervalsToSchedules(List<Interval> intervals) {
        return intervals.stream()
                .collect(Collectors.groupingBy(interval -> interval.getDate().getMonth()))
                .entrySet().stream()
                .map(entry -> ScheduleReportDTO.from(entry.getKey().getValue(), sortIntervals(entry.getValue())))
                .collect(Collectors.toList());
    }

    private List<Interval> sortIntervals(List<Interval> intervals) {
        var comparator = Comparator
                .comparing(Interval::getDate)
                .thenComparing(Interval::getInitialTime)
                .thenComparing(Interval::getFinalTime);
        return intervals.stream().sorted(comparator).collect(Collectors.toList());
    }
}
