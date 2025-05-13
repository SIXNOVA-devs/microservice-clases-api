package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import java.util.List;

public interface CursoService {
    CursoDTO crearCurso(CursoDTO cursoDTO);
    CursoDTO obtenerCursoPorId(Integer id);
    CursoDTO actualizarCurso(Integer id, CursoDTO dto);
    List<CursoDTO> listarCursos();
    void eliminarCurso(Integer id);
}