package br.edu.ufersa.leon.leon.dtos.gym;

import br.edu.ufersa.leon.leon.entities.Gym;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymCreationDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String address;

    public Gym toEntity() {
        return new Gym(null, name, address);
    }
}
