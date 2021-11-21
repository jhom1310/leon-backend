package br.edu.ufersa.leon.leon.dtos.classe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseCreationDTO {
    @NotNull
    private Long gymID;
    @Range(min = 1)
    private double price;
    @NotNull
    private Long teacherID;
    @NotNull
    private Long modalityID;
}
