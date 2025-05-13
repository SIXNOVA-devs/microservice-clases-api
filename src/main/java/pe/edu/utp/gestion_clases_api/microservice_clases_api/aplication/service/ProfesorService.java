package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import java.util.List;

public interface ProfesorService {
    ProfesorDTO crearProfesor(ProfesorDTO profesorDTO);

    List<ProfesorDTO> listarProfesores();

    ProfesorDTO actualizarProfesor(Integer id, ProfesorDTO dto);

    ProfesorDTO obtenerProfesorPorId(Integer id);

    void eliminarProfesor(Integer id);
}