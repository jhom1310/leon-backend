package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.classe.ClasseCreationDTO;
import br.edu.ufersa.leon.leon.dtos.classe.ClasseEditDTO;
import br.edu.ufersa.leon.leon.dtos.classe.ClasseJoinRequestDTO;
import br.edu.ufersa.leon.leon.dtos.core.ClasseDTO;
import br.edu.ufersa.leon.leon.entities.*;
import br.edu.ufersa.leon.leon.repositories.*;
import lombok.AllArgsConstructor;
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
        return createClasse(classeCreation.getGymID(), classeCreation.getTeacherID(), classeCreation.getModalityID(), classeCreation.getPrice())
                .map(classe -> ClasseDTO.fromEntity(classeRepository.save(classe)));
    }

    public Optional<ClasseDTO> edit(Long id, ClasseEditDTO classeEdit) {
        var maybeClasse = classeRepository.findById(id);
        if (maybeClasse.isEmpty()) {
            return Optional.empty();
        }

        var classe = maybeClasse.get();
        var gym = classeEdit.getGymID().flatMap(gymRepository::findById).orElseGet(classe::getGym);
        var modality = classeEdit.getModalityID().flatMap(modalityRepository::findById).orElseGet(classe::getModality);
        var teacher = classeEdit.getTeacherID().flatMap(teacherRepository::findById).orElseGet(classe::getTeacher);
        var price = classeEdit.getPrice().orElseGet(classe::getPrice);
        classe.setGym(gym);
        classe.setModality(modality);
        classe.setTeacher(teacher);
        classe.setPrice(price);
        classe = classeRepository.save(classe);

        return Optional.of(ClasseDTO.fromEntity(classe));
    }

    private Optional<Classe> createClasse(Long gymID, Long teacherID, Long modalityID, double price) {
        return findDependencies(gymID, teacherID, modalityID).map(
                dependencies -> new Classe(null, dependencies.gym, price, dependencies.teacher, List.of(), dependencies.modality, List.of())
        );
    }

    private Optional<ClasseDependencies> findDependencies(Long gymID, Long teacherID, Long modalityID) {
        var gym = gymRepository.findById(gymID).orElse(null);
        var teacher = teacherRepository.findById(teacherID).orElse(null);
        var modality = modalityRepository.findById(modalityID).orElse(null);
        if (gym == null || teacher == null || modality == null) {
            return Optional.empty();
        }
        return Optional.of(new ClasseDependencies(gym, teacher, modality));
    }

    @AllArgsConstructor
    private static class ClasseDependencies {
        Gym gym;
        Teacher teacher;
        Modality modality;
    }
}
