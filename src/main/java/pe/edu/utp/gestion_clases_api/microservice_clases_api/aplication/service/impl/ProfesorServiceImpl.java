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
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Override
    public ProfesorDTO crearProfesor(ProfesorDTO dto) {
        Profesor profesor = Profesor.builder()
                .nombre(dto.getNombre())
                .correoUniversitario(dto.getCorreoUniversitario())
                .build();

        Profesor guardado = profesorRepository.save(profesor);
        dto.setId(guardado.getId());
        return dto;
    }



    @Override
    public List<ProfesorDTO> listarProfesores() {
        return profesorRepository.findAll()
                .stream()
                .map(profesor -> ProfesorDTO.builder()
                        .id(profesor.getId())
                        .nombre(profesor.getNombre())
                        .correoUniversitario(profesor.getCorreoUniversitario())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ProfesorDTO actualizarProfesor(Integer id, ProfesorDTO dto) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        profesor.setNombre(dto.getNombre());
        profesor.setCorreoUniversitario(dto.getCorreoUniversitario());

        return toDTO(profesorRepository.save(profesor));
    }

    @Override
    public void eliminarProfesor(Integer id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        profesorRepository.delete(profesor);
    }

    private ProfesorDTO toDTO(Profesor profesor) {
        return ProfesorDTO.builder()
                .id(profesor.getId())
                .nombre(profesor.getNombre())
                .correoUniversitario(profesor.getCorreoUniversitario())
                .build();
    }

    @Override
    public ProfesorDTO obtenerProfesorPorId(Integer id) {
        Profesor profesor = profesorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        return toDTO(profesor);  // Convertir la entidad a DTO
    }

}