package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.classe.ClasseCreationDTO;
import br.edu.ufersa.leon.leon.dtos.classe.ClasseJoinRequestDTO;
import br.edu.ufersa.leon.leon.dtos.core.ClasseDTO;
import br.edu.ufersa.leon.leon.entities.Classe;
import br.edu.ufersa.leon.leon.entities.User;
import br.edu.ufersa.leon.leon.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClasseService {
    private final ClasseRepository classeRepository;
    private final UserRepository userRepository;
    private final GymRepository gymRepository;
    private final TeacherRepository teacherRepository;
    private final ModalityRepository modalityRepository;

    public ClasseService(ClasseRepository classeRepository, UserRepository userRepository, GymRepository gymRepository, TeacherRepository teacherRepository, ModalityRepository modalityRepository) {
        this.classeRepository = classeRepository;
        this.userRepository = userRepository;
        this.gymRepository = gymRepository;
        this.teacherRepository = teacherRepository;
        this.modalityRepository = modalityRepository;
    }

    public List<ClasseDTO> findUserClasses(Long userID) {
        return classeRepository.findClassesOfUser(userID).stream()
                .map(ClasseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public boolean join(Long classeID, User user, ClasseJoinRequestDTO classeJoinRequest) {
        var userAlreadyJoined = classeRepository.userHasJoinedClasse(classeID, user.getId());
        if (userAlreadyJoined) {
            return false;
        }
        var classe = classeRepository.getById(classeID);
        if (classeJoinRequest.isExperimental()) {
            if (!user.hasAvailableExperiments()) {
                return false;
            }
            user.decrementAvailableExperiments();
        }
        user.getClasses().add(classe);
        userRepository.save(user);
        return true;
    }

    public Optional<ClasseDTO> save(ClasseCreationDTO classeCreation) {
        var gym = gymRepository.findById(classeCreation.getGymID()).orElse(null);
        var teacher = teacherRepository.findById(classeCreation.getTeacherID()).orElse(null);
        var modality = modalityRepository.findById(classeCreation.getModalityID()).orElse(null);
        if (gym == null || teacher == null || modality == null) {
            return Optional.empty();
        }
        var classe = new Classe(null, gym, classeCreation.getPrice(), teacher, List.of(), modality, List.of());
        classe = classeRepository.save(classe);
        return Optional.of(ClasseDTO.fromEntity(classe));
    }
}
