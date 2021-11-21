package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.core.GymDTO;
import br.edu.ufersa.leon.leon.repositories.GymRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymService {
    private final GymRepository gymRepository;

    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public List<GymDTO> findAll() {
        return gymRepository.findAll().stream().map(GymDTO::fromEntity).collect(Collectors.toList());
    }
}
