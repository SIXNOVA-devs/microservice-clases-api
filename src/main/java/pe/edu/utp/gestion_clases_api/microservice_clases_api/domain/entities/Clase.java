package pe.edu.utp.gestion_clases_api.microservice_clases_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "clase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer semana;
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "clase")
    private List<Material> materiales;

    @OneToMany(mappedBy = "clase")
    private List<Tarea> tareas;
}