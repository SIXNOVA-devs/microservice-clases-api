package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import java.util.List;

public interface TareaService {
    TareaDTO crearTarea(TareaDTO tareaDTO);

    List<TareaDTO> obtenerTareasPorClase(Integer claseId);

    TareaDTO actualizarTarea(Integer id, TareaDTO dto);

    void eliminarTarea(Integer id);

}