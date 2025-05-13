package pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.impl;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.domain.entities.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

        private final MaterialRepository materialRepository;
        private final ClaseRepository claseRepository;

        @Override
        public MaterialDTO subirMaterial(MaterialDTO dto) {
                Clase clase = claseRepository.findById(dto.getClaseId())
                                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

                Material material = Material.builder()
                                .titulo(dto.getTitulo())
                                .tipo(dto.getTipo())
                                .urlVisualizacion(dto.getUrlVisualizacion())
                                .urlDescarga(dto.getUrlDescarga())
                                .fechaSubida(dto.getFechaSubida())
                                .clase(clase)
                                .build();

                Material guardado = materialRepository.save(material);
                dto.setId(guardado.getId());
                return dto;
        }

        @Override
        public List<MaterialDTO> obtenerMaterialPorClase(Integer claseId) {
                return materialRepository.findByClaseId(claseId)
                                .stream()
                                .map(material -> MaterialDTO.builder()
                                                .id(material.getId())
                                                .titulo(material.getTitulo())
                                                .tipo(material.getTipo())
                                                .urlVisualizacion(material.getUrlVisualizacion())
                                                .urlDescarga(material.getUrlDescarga())
                                                .fechaSubida(material.getFechaSubida())
                                                .claseId(material.getClase().getId())
                                                .build())
                                .collect(Collectors.toList());
        }

        @Override
        public MaterialDTO actualizarMaterial(Integer id, MaterialDTO dto) {
                Material material = materialRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Material no encontrado"));

                material.setTitulo(dto.getTitulo());
                material.setTipo(dto.getTipo());
                material.setUrlVisualizacion(dto.getUrlVisualizacion());
                material.setUrlDescarga(dto.getUrlDescarga());
                material.setFechaSubida(dto.getFechaSubida());

                Clase clase = claseRepository.findById(dto.getClaseId())
                                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
                material.setClase(clase);

                return toDTO(materialRepository.save(material));
        }

        @Override
        public void eliminarMaterial(Integer id) {
                Material material = materialRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
                materialRepository.delete(material);
        }

        private MaterialDTO toDTO(Material material) {
                return MaterialDTO.builder()
                                .id(material.getId())
                                .titulo(material.getTitulo())
                                .tipo(material.getTipo())
                                .urlVisualizacion(material.getUrlVisualizacion())
                                .urlDescarga(material.getUrlDescarga())
                                .fechaSubida(material.getFechaSubida())
                                .claseId(material.getClase().getId())
                                .build();
        }

}
