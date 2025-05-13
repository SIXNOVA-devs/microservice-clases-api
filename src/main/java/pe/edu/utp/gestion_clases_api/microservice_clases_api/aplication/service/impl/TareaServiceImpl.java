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
public class TareaServiceImpl implements TareaService {

        private final TareaRepository tareaRepository;
        private final ClaseRepository claseRepository;

        @Override
        public TareaDTO crearTarea(TareaDTO dto) {
                Clase clase = claseRepository.findById(dto.getClaseId())
                                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

                Tarea tarea = Tarea.builder()
                                .nombre(dto.getNombre())
                                .fechaInicioEntrega(dto.getFechaInicioEntrega())
                                .fechaFinEntrega(dto.getFechaFinEntrega())
                                .porcentajeNota(dto.getPorcentajeNota())
                                .notaObtenida(dto.getNotaObtenida())
                                .estadoEntrega(dto.getEstadoEntrega())
                                .clase(clase)
                                .build();

                Tarea guardada = tareaRepository.save(tarea);
                dto.setId(guardada.getId());
                return dto;
        }

        @Override
        public List<TareaDTO> obtenerTareasPorClase(Integer claseId) {
                return tareaRepository.findByClaseId(claseId)
                                .stream()
                                .map(tarea -> TareaDTO.builder()
                                                .id(tarea.getId())
                                                .nombre(tarea.getNombre())
                                                .fechaInicioEntrega(tarea.getFechaInicioEntrega())
                                                .fechaFinEntrega(tarea.getFechaFinEntrega())
                                                .porcentajeNota(tarea.getPorcentajeNota())
                                                .notaObtenida(tarea.getNotaObtenida())
                                                .estadoEntrega(tarea.getEstadoEntrega())
                                                .claseId(tarea.getClase().getId())
                                                .build())
                                .collect(Collectors.toList());
        }

        @Override
        public TareaDTO actualizarTarea(Integer id, TareaDTO dto) {
                Tarea tarea = tareaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

                tarea.setNombre(dto.getNombre());
                tarea.setFechaInicioEntrega(dto.getFechaInicioEntrega());
                tarea.setFechaFinEntrega(dto.getFechaFinEntrega());
                tarea.setPorcentajeNota(dto.getPorcentajeNota());
                tarea.setNotaObtenida(dto.getNotaObtenida());
                tarea.setEstadoEntrega(dto.getEstadoEntrega());

                Clase clase = claseRepository.findById(dto.getClaseId())
                                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
                tarea.setClase(clase);

                return toDTO(tareaRepository.save(tarea));
        }

        @Override
        public void eliminarTarea(Integer id) {
                Tarea tarea = tareaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
                tareaRepository.delete(tarea);
        }

        private TareaDTO toDTO(Tarea tarea) {
                return TareaDTO.builder()
                                .id(tarea.getId())
                                .nombre(tarea.getNombre())
                                .fechaInicioEntrega(tarea.getFechaInicioEntrega())
                                .fechaFinEntrega(tarea.getFechaFinEntrega())
                                .porcentajeNota(tarea.getPorcentajeNota())
                                .notaObtenida(tarea.getNotaObtenida())
                                .estadoEntrega(tarea.getEstadoEntrega())
                                .claseId(tarea.getClase().getId())
                                .build();
        }

}