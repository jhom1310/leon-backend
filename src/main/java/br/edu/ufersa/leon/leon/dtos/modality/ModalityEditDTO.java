package br.edu.ufersa.leon.leon.dtos.modality;

import br.edu.ufersa.leon.leon.entities.Modality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalityEditDTO {
    private String name;
    private String description;
    private String imageURL;

    public Modality edit(Modality modality) {
        modality.setName(Optional.ofNullable(name).orElseGet(modality::getName));
        modality.setDescription(Optional.ofNullable(description).orElseGet(modality::getDescription));
        modality.setImageURL(Optional.ofNullable(imageURL).orElseGet(modality::getImageURL));
        return modality;
    }
}
