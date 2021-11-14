package br.edu.ufersa.leon.leon.dtos.user;

import br.edu.ufersa.leon.leon.entities.User;
import lombok.Data;

@Data
public class UserCreatedDTO {
    Long id;
    String email;

    public static UserCreatedDTO fromEntity(User user) {
        var userCreated = new UserCreatedDTO();
        userCreated.setId(user.getId());
        userCreated.setEmail(user.getEmail());
        return userCreated;
    }
}
