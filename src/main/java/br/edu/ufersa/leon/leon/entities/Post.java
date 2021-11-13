package br.edu.ufersa.leon.leon.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String heroSrc;
    private LocalDate publicationDate;
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    public Post(String title, String description, String heroSrc, Admin admin) {
        this.title = title;
        this.description = description;
        this.heroSrc = heroSrc;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHeroSrc() {
        return heroSrc;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Admin getAdmin() {
        return admin;
    }
}
