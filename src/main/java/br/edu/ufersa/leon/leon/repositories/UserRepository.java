package br.edu.ufersa.leon.leon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.leon.leon.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
