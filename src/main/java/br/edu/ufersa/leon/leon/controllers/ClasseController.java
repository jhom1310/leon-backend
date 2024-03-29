package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.classe.ClasseCreationDTO;
import br.edu.ufersa.leon.leon.dtos.classe.ClasseEditDTO;
import br.edu.ufersa.leon.leon.dtos.classe.ClasseJoinRequestDTO;
import br.edu.ufersa.leon.leon.dtos.core.ClasseDTO;
import br.edu.ufersa.leon.leon.services.ClasseService;
import br.edu.ufersa.leon.leon.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClasseController {
    private final ClasseService classeService;
    private final UserService userService;

    public ClasseController(ClasseService classeService, UserService userService) {
        this.classeService = classeService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ClasseDTO> save(@Valid @RequestBody ClasseCreationDTO classeCreation) {
        return classeService.save(classeCreation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClasseDTO> edit(@PathVariable @NotNull Long id, @RequestBody ClasseEditDTO classeEdit) {
        return classeService.edit(id, classeEdit)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClasseDTO>> findAll() {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findUserByEmail(userEmail)
                .map(user -> ResponseEntity.ok(classeService.findUserClasses(user.getId())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<Void> join(
            @PathVariable("id") @NotNull Long classeID,
            @RequestBody @Valid ClasseJoinRequestDTO classeJoinRequest
    ) {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var maybeUser = userService.findUserByEmail(userEmail);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userJoined = classeService.join(classeID, maybeUser.get(), classeJoinRequest);
        if (!userJoined) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
