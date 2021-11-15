package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.modality.ModalityDTO;
import br.edu.ufersa.leon.leon.dtos.modality.PaginatedModalityClassesDTO;
import br.edu.ufersa.leon.leon.services.ModalityService;
import org.springframework.data.domain.Pageable;
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
    public List<ModalityDTO> findAll() {
        return modalityService.findAll().stream().map(ModalityDTO::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModalityDTO> findById(@PathVariable @NotNull Long id) {
        return modalityService.find(id)
                .map(modality -> ResponseEntity.ok(ModalityDTO.fromEntity(modality)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/classes")
    public ResponseEntity<PaginatedModalityClassesDTO> findClasses(
            @PathVariable @NotNull Long id,
            @RequestParam(value = "teacher", required = false) Long teacherID,
            @RequestParam(value = "gym", required = false) Long gymID,
            Pageable pageable
    ) {
        return modalityService.findPaginated(id, teacherID, gymID, pageable)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<ModalityDTO> search(@RequestParam("name") @NotBlank String query) {
        return modalityService.search(query).stream().map(ModalityDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
