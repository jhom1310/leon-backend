package br.edu.ufersa.leon.leon.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ExperimentalClasse {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ExperimentalClasse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
