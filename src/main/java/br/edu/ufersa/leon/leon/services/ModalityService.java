package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.modality.ModalityCreationDTO;
import br.edu.ufersa.leon.leon.dtos.modality.ModalityDTO;
import br.edu.ufersa.leon.leon.dtos.modality.PaginatedModalityClassesDTO;
import br.edu.ufersa.leon.leon.entities.Classe;
import br.edu.ufersa.leon.leon.entities.Modality;
import br.edu.ufersa.leon.leon.repositories.ClasseRepository;
import br.edu.ufersa.leon.leon.repositories.ModalityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ModalityService {
    private final ModalityRepository modalityRepository;
    private final ClasseRepository classeRepository;

    public ModalityService(ModalityRepository modalityRepository, ClasseRepository classeRepository) {
        this.modalityRepository = modalityRepository;
        this.classeRepository = classeRepository;
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

    public List<Modality> search(String query) {
        return modalityRepository.search(query);
    }

    @Transactional(readOnly = true)
    public Optional<PaginatedModalityClassesDTO> findPaginated(Long modalityID, Long teacherID, Long gymID, Pageable pageable) {
        return modalityRepository.findById(modalityID).map(
                modality -> {
                    var teachers = modalityRepository.findTeachers(modalityID);
                    var gyms = modalityRepository.findGyms(modalityID);
                    var classes = findClasses(modalityID, teacherID, gymID, pageable);
                    Objects.requireNonNull(classes);
                    return PaginatedModalityClassesDTO.fromEntity(teachers, gyms, classes);
                }
        );
    }

    private Page<Classe> findClasses(Long modalityID, Long teacherID, Long gymID, Pageable pageable) {
        if (teacherID != null && gymID != null) {
            return classeRepository.findByModalityIdAndTeacherIdAndGymId(modalityID, teacherID, gymID, pageable);
        }
        if (teacherID == null && gymID == null) {
            return classeRepository.findByModalityId(modalityID, PageRequest.of(0, 20));
        }
        if (teacherID != null) {
            return classeRepository.findByModalityIdAndTeacherId(modalityID, teacherID, pageable);
        }
        return classeRepository.findByModalityIdAndGymId(modalityID, gymID, pageable);
    }

    public ModalityDTO save(ModalityCreationDTO modalityCreation) {
        var modality = modalityCreation.toEntity();
        modality = modalityRepository.save(modality);
        return ModalityDTO.fromEntity(modality);
    }
}
