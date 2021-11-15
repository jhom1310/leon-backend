package br.edu.ufersa.leon.leon.dtos.modality;

import br.edu.ufersa.leon.leon.entities.Modality;
import lombok.Data;

@Data
public class ModalityDTO {
    private Long id;
    private String name;
    private String description;
    private String imageURL;

    public static ModalityDTO fromEntity(Modality modality) {
        var dto = new ModalityDTO();
        dto.setId(modality.getId());
        dto.setName(modality.getName());
        dto.setDescription(modality.getDescription());
        dto.setImageURL(modality.getImageURL());
        return dto;
    }
}
