package br.edu.ufersa.leon.leon;

import br.edu.ufersa.leon.leon.entities.*;
import br.edu.ufersa.leon.leon.repositories.ClasseRepository;
import br.edu.ufersa.leon.leon.repositories.GymRepository;
import br.edu.ufersa.leon.leon.repositories.TeacherRepository;
import br.edu.ufersa.leon.leon.services.ModalityService;
import br.edu.ufersa.leon.leon.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class LeonApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeonApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(
            UserService userService,
            ModalityService modalityService,
            GymRepository gymRepository,
            TeacherRepository teacherRepository,
            ClasseRepository classeRepository
    ) {
        return args -> {
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
            userService.save(foo);

            var modalities = modalityService.saveAll(
                    List.of(
                        new Modality(null, "Ioga", "Uma descrição legal", "https://i.imgur.com/7tVmzFD.png", List.of()),
                        new Modality(null, "Karatê", "Karatê kid é top", "https://i.imgur.com/7tVmzFD.png", List.of()),
                        new Modality(null, "Pilates", "Mesma coisa de Ioga", "https://i.imgur.com/7tVmzFD.png", List.of())
                    )
            );
            var ioga = modalities.get(0);
            var gym = new Gym(null, "Academia 1", "Rua dos Bobos");
            gymRepository.save(gym);
            var teacher = new Teacher(null, "Maykon", UserService.DEFAULT_AVATAR_URL);
            teacherRepository.save(teacher);
            var iogaClasse = new Classe(null, gym, 42.0, teacher, List.of(), ioga);
            classeRepository.save(iogaClasse);
        };
    }
}
