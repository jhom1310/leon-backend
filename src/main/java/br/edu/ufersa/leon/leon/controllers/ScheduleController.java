package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.schedule.ScheduleDTO;
import br.edu.ufersa.leon.leon.services.ScheduleService;
import br.edu.ufersa.leon.leon.services.UserService;
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
    public List<ScheduleDTO> findAll() {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.findUserByEmail(userEmail);
        var month = LocalDate.now().getMonthValue();
        return scheduleService.getScheduleOfUser(user, month);
    }

    @GetMapping("/filter")
    public List<ScheduleDTO> findAllBetween(
            @RequestParam(value = "initialMonth") int initialMonth,
            @RequestParam(value = "finalMonth") int finalMonth
    ) {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.findUserByEmail(userEmail);
        return scheduleService.getScheduleOfUserBetween(user, initialMonth, finalMonth);
    }
}
