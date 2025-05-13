package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import java.util.List;

public interface MaterialService {
    MaterialDTO subirMaterial(MaterialDTO materialDTO);

    List<MaterialDTO> obtenerMaterialPorClase(Integer claseId);

    MaterialDTO actualizarMaterial(Integer id, MaterialDTO dto);

    void eliminarMaterial(Integer id);

}