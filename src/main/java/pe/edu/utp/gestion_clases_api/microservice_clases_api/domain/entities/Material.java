package pe.edu.utp.gestion_clases_api.microservice_clases_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "material")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String tipo; // PDF, Video, etc.
    private String urlVisualizacion;
    private String urlDescarga;
    private LocalDate fechaSubida;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;
}
