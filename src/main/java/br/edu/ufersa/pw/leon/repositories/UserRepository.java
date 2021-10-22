package br.edu.ufersa.pw.leon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.pw.leon.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
