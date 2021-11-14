package br.edu.ufersa.leon.leon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modalities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modality {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    @OneToMany(mappedBy = "modality")
    List<Classe> classes = new ArrayList<>();
    // TODO: descobrir como salvar os horários das aulas
}
