package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.core.TeacherDTO;
import br.edu.ufersa.leon.leon.dtos.teacher.TeacherCreationDTO;
import br.edu.ufersa.leon.leon.dtos.teacher.TeacherEditDTO;
import br.edu.ufersa.leon.leon.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDTO save(TeacherCreationDTO teacherCreation) {
        var teacher = teacherCreation.toEntity();
        teacher = teacherRepository.save(teacher);
        return TeacherDTO.fromEntity(teacher);
    }

    public List<TeacherDTO> findAll() {
        return teacherRepository.findAll().stream().map(TeacherDTO::fromEntity).collect(Collectors.toList());
    }

    public Optional<TeacherDTO> edit(Long id, TeacherEditDTO teacherEdit) {
        return teacherRepository.findById(id)
                .map(teacher -> TeacherDTO.fromEntity(teacherRepository.save(teacherEdit.edit(teacher))));
    }
}
