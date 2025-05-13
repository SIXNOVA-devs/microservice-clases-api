package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TareaDTO {
    private Integer id;
    private String nombre;
    private LocalDateTime fechaInicioEntrega;
    private LocalDateTime fechaFinEntrega;
    private float porcentajeNota;
    private float notaObtenida;
    private String estadoEntrega;
    private Integer claseId;
}