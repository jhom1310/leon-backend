package br.edu.ufersa.pw.leon.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String hero_src;
    private LocalDate publication_date;
    @ManyToOne
    @JoinColumn(name = "id_admin")
    private Admin admin_id;


    public Post(Long id, String title, String description, String hero_src, LocalDate publication_date, Admin admin_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hero_src = hero_src;
        this.publication_date = publication_date;
        this.admin_id = admin_id;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHero_src() {
        return this.hero_src;
    }

    public void setHero_src(String hero_src) {
        this.hero_src = hero_src;
    }

    public LocalDate getPublication_date() {
        return this.publication_date;
    }

    public void setPublication_date(LocalDate publication_date) {
        this.publication_date = publication_date;
    }

    public Admin getAdmin_id() {
        return this.admin_id;
    }

    public void setAdmin_id(Admin admin_id) {
        this.admin_id = admin_id;
    }
    
}
