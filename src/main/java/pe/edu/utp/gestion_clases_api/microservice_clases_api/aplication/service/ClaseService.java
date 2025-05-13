package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import java.util.List;

public interface ClaseService {
    ClaseDTO crearClase(ClaseDTO claseDTO);

    List<ClaseDTO> obtenerClasesPorCurso(Integer cursoId);

    List<ClaseDTO> obtenerClasesPorSemana(Integer semana);

    ClaseDTO actualizarClase(Integer id, ClaseDTO dto);

    void eliminarClase(Integer id);
}