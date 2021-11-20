package br.edu.ufersa.leon.leon;

import br.edu.ufersa.leon.leon.entities.*;
import br.edu.ufersa.leon.leon.repositories.ClasseRepository;
import br.edu.ufersa.leon.leon.repositories.GymRepository;
import br.edu.ufersa.leon.leon.repositories.IntervalRepository;
import br.edu.ufersa.leon.leon.repositories.TeacherRepository;
import br.edu.ufersa.leon.leon.services.ModalityService;
import br.edu.ufersa.leon.leon.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeonSeed implements CommandLineRunner {
    UserService userService;
    ModalityService modalityService;
    GymRepository gymRepository;
    TeacherRepository teacherRepository;
    ClasseRepository classeRepository;
    IntervalRepository intervalRepository;

    public LeonSeed(UserService userService, ModalityService modalityService, GymRepository gymRepository, TeacherRepository teacherRepository, ClasseRepository classeRepository, IntervalRepository intervalRepository) {
        this.userService = userService;
        this.modalityService = modalityService;
        this.gymRepository = gymRepository;
        this.teacherRepository = teacherRepository;
        this.classeRepository = classeRepository;
        this.intervalRepository = intervalRepository;
    }

    @Override
    public void run(String... args) {
        usersSeed();

        var modalities = modalityService.saveAll(
                List.of(
                        new Modality(null, "Ioga", "Uma descrição legal", "https://i.imgur.com/7tVmzFD.png", List.of()),
                        new Modality(null, "Karatê", "Karatê kid é top", "https://i.imgur.com/7tVmzFD.png", List.of()),
                        new Modality(null, "Pilates", "Mesma coisa de Ioga", "https://i.imgur.com/7tVmzFD.png", List.of())
                )
        );
        var gym = new Gym(null, "Academia 1", "Rua dos Bobos");
        gymRepository.save(gym);
        var teacher = new Teacher(null, "Maykon", UserService.DEFAULT_AVATAR_URL, List.of());
        teacherRepository.save(teacher);
        var ioga = modalities.get(0);
        var iogaClasse = new Classe(null, gym, 42.0, teacher, new ArrayList<>(), ioga, new ArrayList<>());
        iogaClasse = classeRepository.save(iogaClasse);
        var interval = new Interval(null, LocalDate.now(), LocalTime.of(18, 0), LocalTime.of(19, 0), iogaClasse);
        intervalRepository.save(interval);
        interval = new Interval(null, LocalDate.now().plusDays(1), LocalTime.of(14, 0), LocalTime.of(14, 30), iogaClasse);
        intervalRepository.save(interval);
    }

    private void usersSeed() {
        var userRole = userService.save(new Role(null, RoleType.USER.getName()));
        userService.save(new Role(null, RoleType.ADMIN.getName()));

        var foo = new User();
        foo.setName("John Doe");
        foo.setEmail("foo@gmail.com");
        foo.setAddress("Rua dos Bobos, Nº 69");
        foo.setBirthday(LocalDate.now());
        foo.setPassword("123");
        foo.setAvatarURL("https://i.imgur.com/4JhL9z4.jpg");
        foo.setRoles(List.of(userRole));
        foo = userService.save(foo).get();
    }
}
