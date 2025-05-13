package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.repositories;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Integer> {
    List<Clase> findByCursoId(Integer cursoId);
    List<Clase> findBySemana(Integer semana);
}