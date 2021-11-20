package br.edu.ufersa.leon.leon.dtos.user;

import br.edu.ufersa.leon.leon.entities.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserProfileDTO {
    @NotNull
    Long id;
    @NotBlank
    String name;
    @Email
    String email;
    String address;
    LocalDate birthday;
    @NotBlank
    String avatarURL;
    int availableExperiments;

    public static UserProfileDTO fromEntity(User user) {
        var userProfile = new UserProfileDTO();
        userProfile.setId(user.getId());
        userProfile.setName(user.getName());
        userProfile.setEmail(user.getEmail());
        userProfile.setAddress(user.getAddress());
        userProfile.setBirthday(user.getBirthday());
        userProfile.setAvatarURL(user.getAvatarURL());
        userProfile.setAvailableExperiments(user.getAvailableExperiments());
        return userProfile;
    }
}