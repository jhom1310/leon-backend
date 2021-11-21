package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.core.TeacherDTO;
import br.edu.ufersa.leon.leon.dtos.teacher.TeacherCreationDTO;
import br.edu.ufersa.leon.leon.dtos.teacher.TeacherEditDTO;
import br.edu.ufersa.leon.leon.services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public TeacherDTO save(@Valid @RequestBody TeacherCreationDTO teacherCreation) {
        return teacherService.save(teacherCreation);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeacherDTO> edit(@PathVariable @NotNull Long id, @RequestBody TeacherEditDTO teacherEdit) {
        return teacherService.edit(id, teacherEdit)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TeacherDTO> findAll() {
        return teacherService.findAll();
    }
}
