package br.edu.ufersa.leon.leon.dtos.core;

import br.edu.ufersa.leon.leon.entities.Teacher;
import lombok.Data;

@Data
public class TeacherDTO {
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
