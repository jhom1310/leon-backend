package br.edu.ufersa.pw.leon.repositories;

import br.edu.ufersa.pw.leon.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long>{
    
}
