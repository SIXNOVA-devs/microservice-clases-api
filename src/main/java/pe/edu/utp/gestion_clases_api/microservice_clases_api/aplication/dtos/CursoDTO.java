package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CursoDTO {
    private Integer id;
    private String nombre;
    private String seccion;
    private String modalidad;  // Ej: "Presencial" o "24/7"
    private String zoomUrl;
    private Integer profesorId;
}