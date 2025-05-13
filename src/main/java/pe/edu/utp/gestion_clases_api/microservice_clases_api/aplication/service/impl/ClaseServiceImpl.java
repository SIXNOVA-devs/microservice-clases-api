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
public class ClaseServiceImpl implements ClaseService {

    private final ClaseRepository claseRepository;
    private final CursoRepository cursoRepository;

    @Override
    public ClaseDTO crearClase(ClaseDTO dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Clase clase = Clase.builder()
                .diaSemana(dto.getDiaSemana())
                .horaInicio(dto.getHoraInicio())
                .horaFin(dto.getHoraFin())
                .semana(dto.getSemana())
                .ubicacion(dto.getUbicacion())
                .curso(curso)
                .build();

        Clase claseGuardada = claseRepository.save(clase);
        dto.setId(claseGuardada.getId());
        return dto;
    }

    @Override
    public List<ClaseDTO> obtenerClasesPorCurso(Integer cursoId) {
        return claseRepository.findByCursoId(cursoId)
                .stream()
                .map(clase -> ClaseDTO.builder()
                        .id(clase.getId())
                        .diaSemana(clase.getDiaSemana())
                        .horaInicio(clase.getHoraInicio())
                        .horaFin(clase.getHoraFin())
                        .semana(clase.getSemana())
                        .ubicacion(clase.getUbicacion())
                        .cursoId(clase.getCurso().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ClaseDTO> obtenerClasesPorSemana(Integer semana) {
        return claseRepository.findBySemana(semana)
                .stream()
                .map(clase -> ClaseDTO.builder()
                        .id(clase.getId())
                        .diaSemana(clase.getDiaSemana())
                        .horaInicio(clase.getHoraInicio())
                        .horaFin(clase.getHoraFin())
                        .semana(clase.getSemana())
                        .ubicacion(clase.getUbicacion())
                        .cursoId(clase.getCurso().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ClaseDTO actualizarClase(Integer id, ClaseDTO dto) {
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setDiaSemana(dto.getDiaSemana());
        clase.setHoraInicio(dto.getHoraInicio());
        clase.setHoraFin(dto.getHoraFin());
        clase.setSemana(dto.getSemana());
        clase.setUbicacion(dto.getUbicacion());

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        clase.setCurso(curso);

        return toDTO(claseRepository.save(clase));
    }

    @Override
    public void eliminarClase(Integer id) {
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        claseRepository.delete(clase);
    }

    private ClaseDTO toDTO(Clase clase) {
        return ClaseDTO.builder()
                .id(clase.getId())
                .diaSemana(clase.getDiaSemana())
                .horaInicio(clase.getHoraInicio())
                .horaFin(clase.getHoraFin())
                .semana(clase.getSemana())
                .ubicacion(clase.getUbicacion())
                .cursoId(clase.getCurso().getId())
                .build();
    }

}
