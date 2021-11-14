package br.edu.ufersa.leon.leon.dtos.user;

import br.edu.ufersa.leon.leon.entities.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class EditUserProfileDTO {
    @NotBlank
    String name;
    String address;
    LocalDate birthday;
    @NotBlank
    String avatarURL;

    public static EditUserProfileDTO fromEntity(User user) {
        var editUserProfile = new EditUserProfileDTO();
        editUserProfile.setName(user.getName());
        editUserProfile.setAddress(user.getAddress());
        editUserProfile.setBirthday(user.getBirthday());
        editUserProfile.setAvatarURL(user.getAvatarURL());
        return editUserProfile;
    }
}