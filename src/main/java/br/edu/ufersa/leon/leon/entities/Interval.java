package br.edu.ufersa.leon.leon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "intervals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interval {
    @Id
    @GeneratedValue
    private Long id;
    private LocalTime initialTime;
    private LocalTime finalTime;
    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;
}
