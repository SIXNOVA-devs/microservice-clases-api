package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProfesorDTO {
    private Integer id;
    private String nombre;
    private String correoUniversitario;
}