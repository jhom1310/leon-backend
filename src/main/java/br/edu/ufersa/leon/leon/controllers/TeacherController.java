package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.core.TeacherDTO;
import br.edu.ufersa.leon.leon.dtos.teacher.TeacherCreationDTO;
import br.edu.ufersa.leon.leon.services.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping
    public List<TeacherDTO> findAll() {
        return teacherService.findAll();
    }
}
