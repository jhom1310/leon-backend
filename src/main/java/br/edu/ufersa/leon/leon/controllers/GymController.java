package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.core.GymDTO;
import br.edu.ufersa.leon.leon.dtos.gym.GymCreationDTO;
import br.edu.ufersa.leon.leon.dtos.gym.GymEditDTO;
import br.edu.ufersa.leon.leon.services.GymService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @PatchMapping("/{id}")
    public ResponseEntity<GymDTO> edit(@PathVariable @NotNull Long id, @RequestBody GymEditDTO gymEdit) {
        return gymService.edit(id, gymEdit)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<GymDTO> findAll() {
        return gymService.findAll();
    }
}
