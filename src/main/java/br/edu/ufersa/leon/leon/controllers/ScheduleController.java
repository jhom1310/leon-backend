package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.schedule.ScheduleReportDTO;
import br.edu.ufersa.leon.leon.services.ScheduleService;
import br.edu.ufersa.leon.leon.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final UserService userService;
    private final ScheduleService scheduleService;

    public ScheduleController(UserService userService, ScheduleService scheduleService) {
        this.userService = userService;
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleReportDTO>> findAll() {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findUserByEmail(userEmail).map(
                user -> {
                    var month = LocalDate.now().getMonthValue();
                    var schedules = scheduleService.getSchedulesBetween(user, month, month);
                    return ResponseEntity.ok(schedules);
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ScheduleReportDTO>> findAllBetween(
            @RequestParam(value = "initialMonth") int initialMonth,
            @RequestParam(value = "finalMonth") int finalMonth
    ) {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findUserByEmail(userEmail).map(
                user -> {
                    var schedules = scheduleService.getSchedulesBetween(user, initialMonth, finalMonth);
                    return ResponseEntity.ok(schedules);
                }
        ).orElse(ResponseEntity.notFound().build());
    }
}
