package br.edu.ufersa.pw.leon.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
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

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public int getAvailableReschedules() {
        return availableReschedules;
    }

    public int getAvailableExperiments() {
        return availableExperiments;
    }
}
