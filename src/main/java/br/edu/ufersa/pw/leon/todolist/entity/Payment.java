package br.edu.ufersa.pw.leon.todolist.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_payment")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user_id;
    @ManyToOne
    @JoinColumn(name = "id_classe")
    private Classe class_id;
    private LocalDate payment_day;

    public Payment(Long id, User user_id, Classe class_id, LocalDate payment_day) {
        this.id = id;
        this.user_id = user_id;
        this.class_id = class_id;
        this.payment_day = payment_day;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_id() {
        return this.user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Classe getClass_id() {
        return this.class_id;
    }

    public void setClass_id(Classe class_id) {
        this.class_id = class_id;
    }

    public LocalDate getPayment_day() {
        return this.payment_day;
    }

    public void setPayment_day(LocalDate payment_day) {
        this.payment_day = payment_day;
    }

}
