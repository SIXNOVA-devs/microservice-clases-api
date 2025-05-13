package pe.edu.utp.gestion_clases_api.microservice_clases_api.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.EvaluacionDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.EvaluacionService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EvaluacionServiceTest {

    @Autowired
    private EvaluacionService evaluacionService;

    @Test
    void crearEvaluacion_deberiaGuardarCorrectamente() {
        // Asegúrate de que el curso con ID 1 exista
        EvaluacionDTO dto = EvaluacionDTO.builder()
                .titulo("Examen Final")
                .porcentajeNota(40)
                .fechaInicioEvaluacion(LocalDateTime.now().plusDays(2))
                .fechaFinEvaluacion(LocalDateTime.now().plusDays(3))
                .notaObtenida(0)
                .cursoId(1)
                .build();

        EvaluacionDTO creada = evaluacionService.crearEvaluacion(dto);

        assertNotNull(creada.getId());
        assertEquals("Examen Final", creada.getTitulo());
    }

    @Test
    void obtenerEvaluacionesPorCurso_deberiaRetornarLista() {
        List<EvaluacionDTO> evaluaciones = evaluacionService.obtenerEvaluacionesPorCurso(1); // ID del curso

        assertNotNull(evaluaciones);
        assertFalse(evaluaciones.isEmpty(), "El curso debería tener al menos una evaluación registrada.");
    }

    @Test
    void actualizarEvaluacion_deberiaActualizarCorrectamente() {
        EvaluacionDTO creada = evaluacionService.crearEvaluacion(EvaluacionDTO.builder()
                .titulo("Examen Parcial")
                .porcentajeNota(25)
                .fechaInicioEvaluacion(LocalDateTime.now().plusDays(2))
                .fechaFinEvaluacion(LocalDateTime.now().plusDays(3))
                .notaObtenida(0)
                .cursoId(1)
                .build());

        EvaluacionDTO actualizada = EvaluacionDTO.builder()
                .titulo("Examen Final")
                .porcentajeNota(40)
                .fechaInicioEvaluacion(LocalDateTime.now().plusDays(4))
                .fechaFinEvaluacion(LocalDateTime.now().plusDays(5))
                .notaObtenida(16)
                .cursoId(1)
                .build();

        EvaluacionDTO resultado = evaluacionService.actualizarEvaluacion(creada.getId(), actualizada);

        assertEquals("Examen Final", resultado.getTitulo());
        assertEquals(40, resultado.getPorcentajeNota());
    }

    @Test
    void eliminarEvaluacion_deberiaEliminarCorrectamente() {
        EvaluacionDTO creada = evaluacionService.crearEvaluacion(EvaluacionDTO.builder()
                .titulo("Evaluación Temporal")
                .porcentajeNota(10)
                .fechaInicioEvaluacion(LocalDateTime.now().plusDays(1))
                .fechaFinEvaluacion(LocalDateTime.now().plusDays(2))
                .notaObtenida(0)
                .cursoId(1)
                .build());

        evaluacionService.eliminarEvaluacion(creada.getId());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            evaluacionService.actualizarEvaluacion(creada.getId(), creada);
        });

        assertTrue(ex.getMessage().contains("Evaluación no encontrada"));
    }

}
