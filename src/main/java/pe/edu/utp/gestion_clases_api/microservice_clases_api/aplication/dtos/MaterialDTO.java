package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MaterialDTO {
    private Integer id;
    private String titulo;
    private String tipo; // Ej: "PDF"
    private String urlVisualizacion;
    private String urlDescarga;
    private LocalDate fechaSubida;
    private Integer claseId;
}
