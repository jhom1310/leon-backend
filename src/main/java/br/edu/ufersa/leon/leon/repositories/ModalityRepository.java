package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entities.Modality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModalityRepository extends JpaRepository<Modality, Long> {
    @Query("SELECT m FROM Modality m " +
            "WHERE lower(m.name) LIKE concat('%', lower(:query), '%') OR " +
            " lower(m.description) LIKE concat('%', lower(:query), '%')")
    List<Modality> search(@Param("query") String query);
}
