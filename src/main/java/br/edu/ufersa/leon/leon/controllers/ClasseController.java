package br.edu.ufersa.leon.leon.controllers;

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

    @GetMapping
    public List<ClasseDTO> findAll() {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.findUserByEmail(userEmail);
        return classeService.findUserClasses(user.getId());
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<Void> join(
            @PathVariable("id") @NotNull Long classeID,
            @RequestBody @Valid ClasseJoinRequestDTO classeJoinRequest
    ) {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.findUserByEmail(userEmail);
        var userJoined = classeService.join(classeID, user, classeJoinRequest);
        if (!userJoined) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
