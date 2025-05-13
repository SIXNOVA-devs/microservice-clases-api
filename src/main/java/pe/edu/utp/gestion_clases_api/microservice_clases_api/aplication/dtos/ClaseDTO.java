package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos;

import lombok.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClaseDTO {
    private Integer id;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer semana;
    private String ubicacion;
    private Integer cursoId;
}

