package br.edu.ufersa.leon.leon.dtos.modality;

import br.edu.ufersa.leon.leon.dtos.core.ClasseDTO;
import br.edu.ufersa.leon.leon.dtos.core.GymDTO;
import br.edu.ufersa.leon.leon.dtos.core.TeacherDTO;
import br.edu.ufersa.leon.leon.entities.Classe;
import br.edu.ufersa.leon.leon.entities.Gym;
import br.edu.ufersa.leon.leon.entities.Teacher;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PaginatedModalityClassesDTO {
    private Page<ClasseDTO> classes;
    private List<TeacherDTO> availableTeachers;
    private List<GymDTO> availableGyms;

    public static PaginatedModalityClassesDTO fromEntity(List<Teacher> teachers, List<Gym> gyms, Page<Classe> classes) {
        var dto = new PaginatedModalityClassesDTO();
        dto.setClasses(classes.map(ClasseDTO::fromEntity));
        dto.setAvailableTeachers(teachers.stream().map(TeacherDTO::fromEntity).collect(Collectors.toList()));
        dto.setAvailableGyms(gyms.stream().map(GymDTO::fromEntity).collect(Collectors.toList()));
        return dto;
    }
}
