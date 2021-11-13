package br.edu.ufersa.leon.leon;

import br.edu.ufersa.leon.leon.entities.Role;
import br.edu.ufersa.leon.leon.entities.User;
import br.edu.ufersa.leon.leon.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class LeonApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeonApplication.class, args);
    }

    private UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            var userRole = userService.save(new Role(null, "ROLE_USER"));
            userService.save(new Role(null, "ROLE_ADMIN"));

            var foo = new User();
            foo.setEmail("foo@gmail.com");
            foo.setPassword("123");
            foo.setRoles(List.of(userRole));
            userService.save(foo);
        };
    }
}
