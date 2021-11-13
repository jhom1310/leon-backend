package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.entity.User;
import br.edu.ufersa.leon.leon.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        return ResponseEntity.created(uri).body(new User());
    }
}
