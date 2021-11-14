package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.modality.DetailedModalityDTO;
import br.edu.ufersa.leon.leon.dtos.modality.SimplifiedModalityDTO;
import br.edu.ufersa.leon.leon.services.ModalityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modalities")
public class ModalityController {
    private final ModalityService modalityService;

    public ModalityController(ModalityService modalityService) {
        this.modalityService = modalityService;
    }

    @GetMapping
    public List<SimplifiedModalityDTO> findAll() {
        return modalityService.findAll().stream()
                .map(SimplifiedModalityDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedModalityDTO> findById(@PathVariable @NotNull Long id) {
        return modalityService.find(id)
                .map(modality -> ResponseEntity.ok(DetailedModalityDTO.fromEntity(modality)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<SimplifiedModalityDTO> search(@RequestParam("name") @NotBlank String query) {
        return modalityService.search(query).stream()
                .map(SimplifiedModalityDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
