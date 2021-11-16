package br.edu.ufersa.leon.leon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classe {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;
    private double price;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
    @ManyToMany(mappedBy = "classes")
    private List<User> users = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "modality_id", nullable = false)
    private Modality modality;
    @OneToMany(mappedBy = "classe")
    List<Interval> intervals = new ArrayList<>();
}
