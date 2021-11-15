package br.edu.ufersa.leon.leon.dtos.modality;

import br.edu.ufersa.leon.leon.entities.Classe;
import br.edu.ufersa.leon.leon.entities.Gym;
import br.edu.ufersa.leon.leon.entities.Interval;
import br.edu.ufersa.leon.leon.entities.Teacher;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalTime;
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

    @Data
    static class ClasseDTO {
        private Long id;
        private GymDTO gym;
        private double price;
        private TeacherDTO teacher;
        private List<IntervalDTO> intervals;

        public static ClasseDTO fromEntity(Classe classe) {
            var dto = new ClasseDTO();
            dto.setId(classe.getId());
            dto.setGym(GymDTO.fromEntity(classe.getGym()));
            dto.setPrice(classe.getPrice());
            dto.setTeacher(TeacherDTO.fromEntity(classe.getTeacher()));
            dto.setIntervals(classe.getIntervals().stream().map(IntervalDTO::fromEntity).collect(Collectors.toList()));
            return dto;
        }
    }

    @Data
    static class GymDTO {
        private Long id;
        private String name;
        private String address;

        public static GymDTO fromEntity(Gym gym) {
            var dto = new GymDTO();
            dto.setId(gym.getId());
            dto.setName(gym.getName());
            dto.setAddress(gym.getAddress());
            return dto;
        }
    }

    @Data
    static class TeacherDTO {
        private Long id;
        private String name;
        private String avatarURL;

        public static TeacherDTO fromEntity(Teacher teacher) {
            var dto = new TeacherDTO();
            dto.setId(teacher.getId());
            dto.setName(teacher.getName());
            dto.setAvatarURL(teacher.getAvatarURL());
            return dto;
        }
    }

    @Data
    static class IntervalDTO {
        private Long id;
        private LocalTime initialTime;
        private LocalTime finalTime;

        public static IntervalDTO fromEntity(Interval interval) {
            var dto = new IntervalDTO();
            dto.setId(interval.getId());
            dto.setInitialTime(interval.getInitialTime());
            dto.setFinalTime(interval.getFinalTime());
            return dto;
        }
    }
}
