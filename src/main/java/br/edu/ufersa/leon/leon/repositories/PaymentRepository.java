package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("select p from Payment p where p.user.id = ?1 and p.paymentDay between ?2 and ?3")
    List<Payment> getPaymentsBetween(Long userID, LocalDate initialDate, LocalDate finalDate);
}
