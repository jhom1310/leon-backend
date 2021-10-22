package br.edu.ufersa.pw.leon.repositories;

import br.edu.ufersa.pw.leon.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    
}
