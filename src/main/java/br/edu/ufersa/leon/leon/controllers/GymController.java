package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.core.GymDTO;
import br.edu.ufersa.leon.leon.dtos.gym.GymCreationDTO;
import br.edu.ufersa.leon.leon.services.GymService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gyms")
public class GymController {
    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping
    public GymDTO save(@Valid @RequestBody GymCreationDTO gymCreation) {
        return gymService.save(gymCreation);
    }

    @GetMapping
    public List<GymDTO> findAll() {
        return gymService.findAll();
    }
}
