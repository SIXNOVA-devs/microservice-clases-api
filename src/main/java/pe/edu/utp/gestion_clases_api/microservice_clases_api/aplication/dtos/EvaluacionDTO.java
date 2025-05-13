package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EvaluacionDTO {
    private Integer id;
    private String titulo;
    private float porcentajeNota;
    private LocalDateTime fechaInicioEvaluacion;
    private LocalDateTime fechaFinEvaluacion;
    private float notaObtenida;
    private Integer cursoId;
}
