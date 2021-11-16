package br.edu.ufersa.leon.leon.dtos.core;

import br.edu.ufersa.leon.leon.entities.Classe;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClasseDTO {
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
