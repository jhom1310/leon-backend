package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.user.EditUserProfileDTO;
import br.edu.ufersa.leon.leon.dtos.user.UserProfileDTO;
import br.edu.ufersa.leon.leon.entities.User;
import br.edu.ufersa.leon.leon.services.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.getAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserProfileDTO> findByEmail(@PathVariable @NotNull @Email String email) {
        var authenticatedUserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!authenticatedUserEmail.equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var user = userService.findUserByEmail(email);
        return ResponseEntity.ok(UserProfileDTO.fromEntity(user));
    }

    @PatchMapping("/{email}")
    public void update(@PathVariable @NotNull @Email String email, @Valid @RequestBody EditUserProfileDTO userProfile) {
        userService.update(email, userProfile);
    }

    @PostMapping
    public ResponseEntity<UserCreatedDTO> save(@Valid @RequestBody UserCreationDTO userCreation) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        return userService.save(userCreation.asEntity())
            .map(user -> {
                var userCreated = UserCreatedDTO.fromEntity(user);
                return ResponseEntity.created(uri).body(userCreated);
            }).orElseGet(ResponseEntity.status(HttpStatus.CONFLICT)::build);
    }
}

@Data
class UserCreationDTO {
    @Email(message = "Invalid email")
    String email;
    @NotBlank(message = "Password can't be null or empty")
    String password;

    User asEntity() {
        var user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}

@Data
class UserCreatedDTO {
    Long id;
    String email;

    static UserCreatedDTO fromEntity(User user) {
        var userCreated = new UserCreatedDTO();
        userCreated.setId(user.getId());
        userCreated.setEmail(user.getEmail());
        return userCreated;
    }
}
