package pe.edu.utp.gestion_clases_api.microservice_clases_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "profesor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String correoUniversitario;

    @OneToMany(mappedBy = "profesor")
    private List<Curso> cursos;


    
}