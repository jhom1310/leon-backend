package br.edu.ufersa.leon.leon.dtos.modality;

import br.edu.ufersa.leon.leon.entities.Modality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalityCreationDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String imageURL;

    public Modality toEntity() {
        return new Modality(null, name, description, imageURL, List.of());
    }
}
