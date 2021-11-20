package br.edu.ufersa.leon.leon.dtos.classe;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClasseJoinRequestDTO {
    @NotNull
    private boolean experimental;
}
