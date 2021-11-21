package br.edu.ufersa.leon.leon.dtos.user;

import br.edu.ufersa.leon.leon.entities.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
public class EditUserProfileDTO {
    String name;
    String address;
    LocalDate birthday;
    String avatarURL;

    public User edit(User user) {
        user.setName(Optional.ofNullable(name).orElseGet(user::getName));
        user.setAddress(Optional.ofNullable(address).orElseGet(user::getAddress));
        user.setBirthday(Optional.ofNullable(birthday).orElseGet(user::getBirthday));
        user.setAvatarURL(Optional.ofNullable(avatarURL).orElseGet(user::getAvatarURL));
        return user;
    }
}