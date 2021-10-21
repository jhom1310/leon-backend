package br.edu.ufersa.pw.leon.todolist.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_classe")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_gym")
    private Gym gym_id;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher_id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user_id;
    @ManyToOne
    @JoinColumn(name = "id_modality")
    private Modality modality_id;



    public Classe(Long id, Gym gym_id, Double price, Teacher teacher_id, User user_id, Modality modality_id) {
        this.id = id;
        this.gym_id = gym_id;
        this.price = price;
        this.teacher_id = teacher_id;
        this.user_id = user_id;
        this.modality_id = modality_id;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gym getGym_id() {
        return this.gym_id;
    }

    public void setGym_id(Gym gym_id) {
        this.gym_id = gym_id;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Teacher getTeacher_id() {
        return this.teacher_id;
    }

    public void setTeacher_id(Teacher teacher_id) {
        this.teacher_id = teacher_id;
    }

    public User getUser_id() {
        return this.user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Modality getModality_id() {
        return this.modality_id;
    }

    public void setModality_id(Modality modality_id) {
        this.modality_id = modality_id;
    }


    
}
