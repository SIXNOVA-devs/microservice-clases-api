package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.repositories;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}