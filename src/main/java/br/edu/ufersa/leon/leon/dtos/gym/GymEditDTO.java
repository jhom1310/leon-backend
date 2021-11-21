package br.edu.ufersa.leon.leon.dtos.gym;

import br.edu.ufersa.leon.leon.entities.Gym;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymEditDTO {
    private String name;
    private String address;

    public Gym edit(Gym gym) {
        gym.setName(Optional.ofNullable(name).orElseGet(gym::getName));
        gym.setAddress(Optional.ofNullable(address).orElseGet(gym::getAddress));
        return gym;
    }
}
