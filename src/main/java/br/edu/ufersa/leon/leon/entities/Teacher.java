package br.edu.ufersa.leon.leon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String avatarURL;
    @OneToMany(mappedBy = "teacher")
    List<Classe> classes = new ArrayList<>();
}
