package br.edu.ufersa.pw.leon.repositories;

import br.edu.ufersa.pw.leon.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}
