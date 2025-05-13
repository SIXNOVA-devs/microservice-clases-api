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
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;

    @Override
    public CursoDTO crearCurso(CursoDTO dto) {
        Profesor profesor = profesorRepository.findById(dto.getProfesorId())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        Curso curso = Curso.builder()
                .nombre(dto.getNombre())
                .seccion(dto.getSeccion())
                .modalidad(dto.getModalidad())
                .zoomUrl(dto.getZoomUrl())
                .profesor(profesor)
                .build();

        Curso cursoGuardado = cursoRepository.save(curso);
        dto.setId(cursoGuardado.getId());
        return dto;
    }

    @Override
    public CursoDTO obtenerCursoPorId(Integer id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        return CursoDTO.builder()
                .id(curso.getId())
                .nombre(curso.getNombre())
                .seccion(curso.getSeccion())
                .modalidad(curso.getModalidad())
                .zoomUrl(curso.getZoomUrl())
                .profesorId(curso.getProfesor().getId())
                .build();
    }

    @Override
    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll().stream().map(curso -> CursoDTO.builder()
                .id(curso.getId())
                .nombre(curso.getNombre())
                .seccion(curso.getSeccion())
                .modalidad(curso.getModalidad())
                .zoomUrl(curso.getZoomUrl())
                .profesorId(curso.getProfesor().getId())
                .build()).collect(Collectors.toList());
    }

    @Override
    public CursoDTO actualizarCurso(Integer id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        curso.setNombre(dto.getNombre());
        curso.setSeccion(dto.getSeccion());
        curso.setModalidad(dto.getModalidad());
        curso.setZoomUrl(dto.getZoomUrl());
        Profesor profesor = profesorRepository.findById(dto.getProfesorId())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        curso.setProfesor(profesor);
        
        return toDTO(cursoRepository.save(curso));
    }

    @Override
    public void eliminarCurso(Integer id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        cursoRepository.delete(curso);
    }

    private CursoDTO toDTO(Curso curso) {
        return CursoDTO.builder()
                .id(curso.getId())
                .nombre(curso.getNombre())
                .seccion(curso.getSeccion())
                .modalidad(curso.getModalidad())
                .zoomUrl(curso.getZoomUrl())
                .profesorId(curso.getProfesor().getId())
                .build();
    }

}
