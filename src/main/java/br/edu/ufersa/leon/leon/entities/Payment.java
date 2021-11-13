package br.edu.ufersa.leon.leon.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe_id;
    private LocalDate paymentDay;

    public Payment(User user, Classe classe_id, LocalDate paymentDay) {
        this.user = user;
        this.classe_id = classe_id;
        this.paymentDay = paymentDay;
    }

    public Payment() {

    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Classe getClasse_id() {
        return classe_id;
    }

    public LocalDate getPaymentDay() {
        return paymentDay;
    }
}
