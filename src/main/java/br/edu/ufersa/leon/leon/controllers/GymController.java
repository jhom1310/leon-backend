package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.core.GymDTO;
import br.edu.ufersa.leon.leon.services.GymService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gyms")
public class GymController {
    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping
    public List<GymDTO> findAll() {
        return gymService.findAll();
    }
}
