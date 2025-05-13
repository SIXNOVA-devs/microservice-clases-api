package pe.edu.utp.gestion_clases_api.microservice_clases_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarea")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private LocalDateTime fechaInicioEntrega;
    private LocalDateTime fechaFinEntrega;
    private float porcentajeNota;
    private float notaObtenida;
    private String estadoEntrega;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;
}