package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.classe.ClasseJoinRequestDTO;
import br.edu.ufersa.leon.leon.dtos.core.ClasseDTO;
import br.edu.ufersa.leon.leon.entities.User;
import br.edu.ufersa.leon.leon.repositories.ClasseRepository;
import br.edu.ufersa.leon.leon.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseService {
    private final ClasseRepository classeRepository;
    private final UserRepository userRepository;

    public ClasseService(ClasseRepository classeRepository, UserRepository userRepository) {
        this.classeRepository = classeRepository;
        this.userRepository = userRepository;
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
}
