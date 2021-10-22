package br.edu.ufersa.pw.leon.main;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.ufersa.pw.leon.repositories.UserRepository;

@SpringBootApplication
public class LeonApplication {
	@Autowired
	UserRepository userRepo;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LeonApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LeonApplication.class, args);
		logger.info("Iniciando Aplicação");
	}
	@Bean
	CommandLineRunner runner(){
		return args -> {
			/* User user = new User();
			user.setEmail("aleffjonatha7@gmail.com");
			user.setPassword("password");
			userRepo.save(user); */
		};
	}

}
