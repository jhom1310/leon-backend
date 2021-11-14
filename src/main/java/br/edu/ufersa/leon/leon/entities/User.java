package br.edu.ufersa.leon.leon.entities;

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
    private String address;
    private LocalDate birthday;
    private String password;
    private String cellphone;
    private String avatarURL;
    private int availableReschedules;
    private int availableExperiments;
    @OneToMany(mappedBy = "user")
    List<Payment> payments = new ArrayList<>();
    // TODO: descobrir como adicionar relacionamento com ExperimentalClasse
    @ManyToMany
    @JoinTable(
            name = "users_classes",
            joinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            referencedColumnName = "id",
                            nullable = false,
                            updatable = false
                    ),
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "classe_id",
                            referencedColumnName = "id",
                            nullable = false,
                            updatable = false
                    ),
            }
    )
    List<Classe> classes = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
