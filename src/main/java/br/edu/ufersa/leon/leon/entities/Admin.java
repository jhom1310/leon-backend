package br.edu.ufersa.leon.leon.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @OneToMany(mappedBy = "admin")
    private List<Post> posts;

    public Admin(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
