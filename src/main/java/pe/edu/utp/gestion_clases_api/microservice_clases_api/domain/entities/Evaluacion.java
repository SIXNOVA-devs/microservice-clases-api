package pe.edu.utp.gestion_clases_api.microservice_clases_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private float porcentajeNota;
    private LocalDateTime fechaInicioEvaluacion;
    private LocalDateTime fechaFinEvaluacion;
    private float notaObtenida;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}