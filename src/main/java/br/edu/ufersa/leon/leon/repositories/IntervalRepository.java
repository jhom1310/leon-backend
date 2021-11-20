package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entities.Interval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IntervalRepository extends JpaRepository<Interval, Long> {
    @Query("select i from Interval i left join i.classe.users users where users.id = ?1 and i.date between ?2 and ?3 order by i.date, i.initialTime, i.finalTime")
    List<Interval> getScheduleBetween(Long userID, LocalDate initialDate, LocalDate finalDate);
}
