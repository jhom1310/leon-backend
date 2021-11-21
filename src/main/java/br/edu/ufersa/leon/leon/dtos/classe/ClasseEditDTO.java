package br.edu.ufersa.leon.leon.dtos.classe;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ClasseEditDTO {
    private Long gymID;
    private Double price;
    private Long teacherID;
    private Long modalityID;

    public Optional<Long> getGymID() {
        return Optional.ofNullable(gymID);
    }

    public Optional<Double> getPrice() {
        return Optional.ofNullable(price);
    }

    public Optional<Long> getTeacherID() {
        return Optional.ofNullable(teacherID);
    }

    public Optional<Long> getModalityID() {
        return Optional.ofNullable(modalityID);
    }
}
