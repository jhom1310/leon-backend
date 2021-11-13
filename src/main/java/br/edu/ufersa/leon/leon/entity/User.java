package br.edu.ufersa.leon.leon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate birthday;
    private String password;
    private String cellphone;
    private String avatarURL;
    private int availableReschedules;
    private int availableExperiments;
    @OneToMany(mappedBy = "user")
    List<Payment> payments;
    // TODO: descobrir como adicionar relacionamento com ExperimentalClasse
    @OneToMany(mappedBy = "user")
    List<Classe> classes;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
