package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entities.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
    Page<Classe> findByModalityId(Long modalityID, Pageable pageable);
    Page<Classe> findByModalityIdAndTeacherIdAndGymId(Long modalityID, Long teacherID, Long gymID, Pageable pageable);
    Page<Classe> findByModalityIdAndTeacherId(Long modalityID, Long teacherID, Pageable pageable);
    Page<Classe> findByModalityIdAndGymId(Long modalityID, Long gymID, Pageable pageable);
}
