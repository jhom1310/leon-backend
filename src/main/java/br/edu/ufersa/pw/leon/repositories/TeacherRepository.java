package br.edu.ufersa.pw.leon.repositories;

import br.edu.ufersa.pw.leon.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    
}
