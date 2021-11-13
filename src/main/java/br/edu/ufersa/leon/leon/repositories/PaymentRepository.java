package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
