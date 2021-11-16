package br.edu.ufersa.leon.leon.dtos.core;

import br.edu.ufersa.leon.leon.entities.Gym;
import lombok.Data;

@Data
public class GymDTO {
    private Long id;
    private String name;
    private String address;

    public static GymDTO fromEntity(Gym gym) {
        var dto = new GymDTO();
        dto.setId(gym.getId());
        dto.setName(gym.getName());
        dto.setAddress(gym.getAddress());
        return dto;
    }
}
