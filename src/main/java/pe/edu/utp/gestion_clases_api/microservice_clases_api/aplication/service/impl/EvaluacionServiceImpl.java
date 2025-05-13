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
public class EvaluacionServiceImpl implements EvaluacionService {

        private final EvaluacionRepository evaluacionRepository;
        private final CursoRepository cursoRepository;

        @Override
        public EvaluacionDTO crearEvaluacion(EvaluacionDTO dto) {
                Curso curso = cursoRepository.findById(dto.getCursoId())
                                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

                Evaluacion evaluacion = Evaluacion.builder()
                                .titulo(dto.getTitulo())
                                .porcentajeNota(dto.getPorcentajeNota())
                                .fechaInicioEvaluacion(dto.getFechaInicioEvaluacion())
                                .fechaFinEvaluacion(dto.getFechaFinEvaluacion())
                                .notaObtenida(dto.getNotaObtenida())
                                .curso(curso)
                                .build();

                Evaluacion guardada = evaluacionRepository.save(evaluacion);
                dto.setId(guardada.getId());
                return dto;
        }

        @Override
        public List<EvaluacionDTO> obtenerEvaluacionesPorCurso(Integer cursoId) {
                return evaluacionRepository.findByCursoId(cursoId)
                                .stream()
                                .map(e -> EvaluacionDTO.builder()
                                                .id(e.getId())
                                                .titulo(e.getTitulo())
                                                .porcentajeNota(e.getPorcentajeNota())
                                                .fechaInicioEvaluacion(e.getFechaInicioEvaluacion())
                                                .fechaFinEvaluacion(e.getFechaFinEvaluacion())
                                                .notaObtenida(e.getNotaObtenida())
                                                .cursoId(e.getCurso().getId())
                                                .build())
                                .collect(Collectors.toList());
        }

        @Override
        public EvaluacionDTO actualizarEvaluacion(Integer id, EvaluacionDTO dto) {
                Evaluacion evaluacion = evaluacionRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));

                evaluacion.setTitulo(dto.getTitulo());
                evaluacion.setPorcentajeNota(dto.getPorcentajeNota());
                evaluacion.setFechaInicioEvaluacion(dto.getFechaInicioEvaluacion());
                evaluacion.setFechaFinEvaluacion(dto.getFechaFinEvaluacion());
                evaluacion.setNotaObtenida(dto.getNotaObtenida());

                Curso curso = cursoRepository.findById(dto.getCursoId())
                                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
                evaluacion.setCurso(curso);

                return toDTO(evaluacionRepository.save(evaluacion));
        }

        @Override
        public void eliminarEvaluacion(Integer id) {
                Evaluacion evaluacion = evaluacionRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
                evaluacionRepository.delete(evaluacion);
        }

        private EvaluacionDTO toDTO(Evaluacion evaluacion) {
                return EvaluacionDTO.builder()
                                .id(evaluacion.getId())
                                .titulo(evaluacion.getTitulo())
                                .porcentajeNota(evaluacion.getPorcentajeNota())
                                .fechaInicioEvaluacion(evaluacion.getFechaInicioEvaluacion())
                                .fechaFinEvaluacion(evaluacion.getFechaFinEvaluacion())
                                .notaObtenida(evaluacion.getNotaObtenida())
                                .cursoId(evaluacion.getCurso().getId())
                                .build();
        }

}
