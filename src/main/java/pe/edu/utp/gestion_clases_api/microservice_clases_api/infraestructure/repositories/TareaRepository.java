package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.repositories;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByClaseId(Integer claseId);
}