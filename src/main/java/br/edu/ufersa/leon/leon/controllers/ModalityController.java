package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.entities.Modality;
import br.edu.ufersa.leon.leon.services.ModalityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/modalities")
public class ModalityController {
    private final ModalityService modalityService;

    public ModalityController(ModalityService modalityService) {
        this.modalityService = modalityService;
    }

    @GetMapping
    public List<Modality> findAll() {
        return modalityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modality> findById(@PathVariable @NotNull Long id) {
        return modalityService.find(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Modality> search(@RequestParam("name") @NotBlank String query) {
        return modalityService.search(query);
    }
}
