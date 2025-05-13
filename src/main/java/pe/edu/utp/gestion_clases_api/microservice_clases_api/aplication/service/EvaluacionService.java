package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import java.util.List;

public interface EvaluacionService {
    EvaluacionDTO crearEvaluacion(EvaluacionDTO evaluacionDTO);

    List<EvaluacionDTO> obtenerEvaluacionesPorCurso(Integer cursoId);

    EvaluacionDTO actualizarEvaluacion(Integer id, EvaluacionDTO dto);

    void eliminarEvaluacion(Integer id);

}