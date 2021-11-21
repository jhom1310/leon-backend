package br.edu.ufersa.leon.leon.dtos.teacher;

import br.edu.ufersa.leon.leon.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCreationDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String avatarURL;

    public Teacher toEntity() {
        return new Teacher(null, name, avatarURL, List.of());
    }
}
