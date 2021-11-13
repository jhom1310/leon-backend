package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.entities.Modality;
import br.edu.ufersa.leon.leon.services.ModalityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return modalityService.getAll();
    }
}
