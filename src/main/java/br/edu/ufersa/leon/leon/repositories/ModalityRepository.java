package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entities.Gym;
import br.edu.ufersa.leon.leon.entities.Modality;
import br.edu.ufersa.leon.leon.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModalityRepository extends JpaRepository<Modality, Long> {
    @Query("SELECT m FROM Modality m " +
            "WHERE lower(m.name) LIKE concat('%', lower(:query), '%') OR " +
            " lower(m.description) LIKE concat('%', lower(:query), '%')")
    List<Modality> search(@Param("query") String query);

    @Query(
            value =
                    "SELECT t FROM Modality m, Classe c, Teacher t " +
                    "WHERE m.id = c.modality.id AND " +
                    "c.teacher.id = t.id AND " +
                    "m.id = :modalityID",
            countQuery =
                    "SELECT t FROM Modality m, Classe c, Teacher t " +
                    "WHERE m.id = c.modality.id AND " +
                    "c.teacher.id = t.id AND " +
                    "m.id = :modalityID"
    )
    List<Teacher> findTeachers(@Param("modalityID") Long modalityID);

    @Query(
            value =
                    "SELECT g FROM Modality m, Classe c, Gym g " +
                    "WHERE m.id = c.modality.id AND " +
                    "c.gym.id = g.id AND " +
                    "m.id = :gymID",
            countQuery =
                    "SELECT count(g) FROM Modality m, Classe c, Gym g " +
                    "WHERE m.id = c.modality.id AND " +
                    "c.gym.id = g.id AND " +
                    "m.id = :gymID"
    )
    List<Gym> findGyms(@Param("gymID") Long gymID);
}
