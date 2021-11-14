package br.edu.ufersa.leon.leon.dtos.modality;

import br.edu.ufersa.leon.leon.entities.Classe;
import br.edu.ufersa.leon.leon.entities.Gym;
import br.edu.ufersa.leon.leon.entities.Modality;
import br.edu.ufersa.leon.leon.entities.Teacher;
import lombok.Data;

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

        public static ClasseDTO fromEntity(Classe classe) {
            var dto = new ClasseDTO();
            dto.setId(classe.getId());
            dto.setGym(classe.getGym());
            dto.setPrice(classe.getPrice());
            dto.setTeacher(classe.getTeacher());
            return dto;
        }
    }
}
