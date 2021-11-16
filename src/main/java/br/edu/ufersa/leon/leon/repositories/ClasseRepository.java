package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entities.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
    Page<Classe> findByModalityId(Long modalityID, Pageable pageable);
    Page<Classe> findByModalityIdAndTeacherIdAndGymId(Long modalityID, Long teacherID, Long gymID, Pageable pageable);
    Page<Classe> findByModalityIdAndTeacherId(Long modalityID, Long teacherID, Pageable pageable);
    Page<Classe> findByModalityIdAndGymId(Long modalityID, Long gymID, Pageable pageable);
    /*@Query("SELECT c FROM Classe c, User u,  uc " +
           "WHERE c.id = uc.user_id AND " +
           "uc.user_id = u.id AND " +
           "u.id = :userID"
    )
    List<Classe> findByUserId(Long userID);*/

    @Query("select c from Classe c left join c.users users where users.id = ?1")
    List<Classe> findClassesOfUser(@NonNull Long userID);

    //@Query("select c from Classe c left join c.users users where c.id = ?1 and users.id = ?2")
    //Optional<Classe> findClasseOfUser(Long classeID, Long userID);

    @Query("select (count(c) > 0) from Classe c left join c.users users where c.id = ?1 and users.id = ?2")
    boolean userHasJoinedClasse(Long classeID, Long userID);
}
