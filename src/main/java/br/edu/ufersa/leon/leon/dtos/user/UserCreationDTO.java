package br.edu.ufersa.leon.leon.dtos.user;

import br.edu.ufersa.leon.leon.entities.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserCreationDTO {
    @Email(message = "Invalid email")
    String email;
    @NotBlank(message = "Password can't be null or empty")
    String password;

    public User asEntity() {
        var user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
