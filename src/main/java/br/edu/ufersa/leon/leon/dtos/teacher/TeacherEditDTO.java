package br.edu.ufersa.leon.leon.dtos.teacher;

import br.edu.ufersa.leon.leon.entities.Teacher;
import lombok.Data;

import java.util.Optional;

@Data
public class TeacherEditDTO {
    private String name;
    private String avatarURL;

    public Teacher edit(Teacher teacher) {
        teacher.setName(Optional.ofNullable(name).orElseGet(teacher::getName));
        teacher.setAvatarURL(Optional.ofNullable(avatarURL).orElseGet(teacher::getAvatarURL));
        return teacher;
    }
}
