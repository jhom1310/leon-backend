package br.edu.ufersa.leon.leon.dtos.classe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseJoinRequestDTO {
    @NotNull
    private boolean experimental;
}
