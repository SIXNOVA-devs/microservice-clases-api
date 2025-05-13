package pe.edu.utp.gestion_clases_api.microservice_clases_api.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.ClaseDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.ClaseService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClaseServiceTest {

    @Autowired
    private ClaseService claseService;

    @Test
    void crearClase_deberiaGuardarCorrectamente() {
        // Asegúrate de que el curso con ID 1 exista
        ClaseDTO dto = ClaseDTO.builder()
                .diaSemana("Martes")
                .horaInicio(LocalTime.of(10, 0))
                .horaFin(LocalTime.of(12, 0))
                .semana(9)
                .ubicacion("Laboratorio 201")
                .cursoId(1)
                .build();

        ClaseDTO creada = claseService.crearClase(dto);

        assertNotNull(creada.getId());
        assertEquals("Martes", creada.getDiaSemana());
    }

    @Test
    void obtenerClasesPorCurso_deberiaRetornarResultados() {
        List<ClaseDTO> clases = claseService.obtenerClasesPorCurso(1); // ID del curso
        assertNotNull(clases);
        assertFalse(clases.isEmpty(), "La lista no debería estar vacía si hay clases creadas.");
    }

    @Test
    void actualizarClase_deberiaActualizarCorrectamente() {
        // Crear clase base
        ClaseDTO creada = claseService.crearClase(ClaseDTO.builder()
                .diaSemana("Martes")
                .horaInicio(LocalTime.of(9, 0))
                .horaFin(LocalTime.of(11, 0))
                .semana(10)
                .ubicacion("Aula A")
                .cursoId(1)
                .build());

        // Datos nuevos
        ClaseDTO actualizada = ClaseDTO.builder()
                .diaSemana("Viernes")
                .horaInicio(LocalTime.of(13, 0))
                .horaFin(LocalTime.of(15, 0))
                .semana(12)
                .ubicacion("Aula B")
                .cursoId(1)
                .build();

        ClaseDTO resultado = claseService.actualizarClase(creada.getId(), actualizada);

        assertEquals("Viernes", resultado.getDiaSemana());
        assertEquals("Aula B", resultado.getUbicacion());
    }

    @Test
    void eliminarClase_deberiaEliminarCorrectamente() {
        ClaseDTO creada = claseService.crearClase(ClaseDTO.builder()
                .diaSemana("Miércoles")
                .horaInicio(LocalTime.of(10, 0))
                .horaFin(LocalTime.of(12, 0))
                .semana(9)
                .ubicacion("Aula C")
                .cursoId(1)
                .build());

        claseService.eliminarClase(creada.getId());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            claseService.actualizarClase(creada.getId(), creada);
        });

        assertTrue(ex.getMessage().contains("Clase no encontrada"));
    }

}
