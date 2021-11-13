package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.entities.Modality;
import br.edu.ufersa.leon.leon.repositories.ModalityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModalityService {
    private final ModalityRepository modalityRepository;

    public ModalityService(ModalityRepository modalityRepository) {
        this.modalityRepository = modalityRepository;
    }

    public List<Modality> findAll() {
        return modalityRepository.findAll();
    }

    public List<Modality> saveAll(List<Modality> modalities) {
        return modalityRepository.saveAll(modalities);
    }

    public Optional<Modality> find(Long id) {
        return modalityRepository.findById(id);
    }
}
