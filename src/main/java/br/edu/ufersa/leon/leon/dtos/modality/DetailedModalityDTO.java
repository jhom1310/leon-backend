package br.edu.ufersa.leon.leon.dtos.modality;

import br.edu.ufersa.leon.leon.entities.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetailedModalityDTO {
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private List<ClasseDTO> classes;

    public static DetailedModalityDTO fromEntity(Modality modality) {
        var dto = new DetailedModalityDTO();
        dto.setId(modality.getId());
        dto.setName(modality.getName());
        dto.setDescription(modality.getDescription());
        dto.setImageURL(modality.getImageURL());
        dto.setClasses(modality.getClasses().stream().map(ClasseDTO::fromEntity).collect(Collectors.toList()));
        return dto;
    }

    @Data
    static class ClasseDTO {
        private Long id;
        private Gym gym;
        private double price;
        private Teacher teacher;
        private List<IntervalDTO> intervals;

        public static ClasseDTO fromEntity(Classe classe) {
            var dto = new ClasseDTO();
            dto.setId(classe.getId());
            dto.setGym(classe.getGym());
            dto.setPrice(classe.getPrice());
            dto.setTeacher(classe.getTeacher());
            dto.setIntervals(classe.getIntervals().stream().map(IntervalDTO::fromEntity).collect(Collectors.toList()));
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
