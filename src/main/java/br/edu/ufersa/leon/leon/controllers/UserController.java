package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.user.EditUserProfileDTO;
import br.edu.ufersa.leon.leon.dtos.user.UserCreatedDTO;
import br.edu.ufersa.leon.leon.dtos.user.UserCreationDTO;
import br.edu.ufersa.leon.leon.dtos.user.UserProfileDTO;
import br.edu.ufersa.leon.leon.entities.RoleType;
import br.edu.ufersa.leon.leon.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Email;
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
    public List<UserProfileDTO> findAll() {
        return userService.getAll();
    }

    private boolean currentUserHasEmail(String email) {
        var authenticatedUserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authenticatedUserEmail.equals(email);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserProfileDTO> findProfileByEmail(@PathVariable @NotNull @Email String email) {
        if (!currentUserHasEmail(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return userService.findProfileByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{email}")
    public ResponseEntity<UserProfileDTO> edit(@PathVariable @NotNull @Email String email, @Valid @RequestBody EditUserProfileDTO userProfile) {
        return userService.edit(email, userProfile)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserCreatedDTO> save(@Valid @RequestBody UserCreationDTO userCreation) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        return userService.save(userCreation.asEntity(), RoleType.USER)
                .map(user -> {
                    var userCreated = UserCreatedDTO.fromEntity(user);
                    return ResponseEntity.created(uri).body(userCreated);
                }).orElseGet(ResponseEntity.status(HttpStatus.CONFLICT)::build);
    }
}
