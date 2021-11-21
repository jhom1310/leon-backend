package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.core.GymDTO;
import br.edu.ufersa.leon.leon.dtos.gym.GymCreationDTO;
import br.edu.ufersa.leon.leon.dtos.gym.GymEditDTO;
import br.edu.ufersa.leon.leon.repositories.GymRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GymService {
    private final GymRepository gymRepository;

    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public GymDTO save(GymCreationDTO gymCreation) {
        var gym = gymCreation.toEntity();
        gym = gymRepository.save(gym);
        return GymDTO.fromEntity(gym);
    }

    public List<GymDTO> findAll() {
        return gymRepository.findAll().stream().map(GymDTO::fromEntity).collect(Collectors.toList());
    }

    public Optional<GymDTO> edit(Long id, GymEditDTO gymEdit) {
        return gymRepository.findById(id).map(gym -> GymDTO.fromEntity(gymRepository.save(gymEdit.edit(gym))));
    }
}
