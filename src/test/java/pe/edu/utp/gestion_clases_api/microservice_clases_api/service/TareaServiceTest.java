package pe.edu.utp.gestion_clases_api.microservice_clases_api.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.TareaDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.TareaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TareaServiceTest {

    @Autowired
    private TareaService tareaService;

    @Test
    void crearTarea_deberiaGuardarCorrectamente() {
        // Asegúrate que exista una clase con ID 1
        TareaDTO dto = TareaDTO.builder()
                .nombre("Tarea #1 - Propuesta")
                .fechaInicioEntrega(LocalDateTime.now())
                .fechaFinEntrega(LocalDateTime.now().plusDays(3))
                .porcentajeNota(15)
                .notaObtenida(0)
                .estadoEntrega("Pendiente")
                .claseId(1)
                .build();

        TareaDTO creada = tareaService.crearTarea(dto);

        assertNotNull(creada.getId());
        assertEquals("Tarea #1 - Propuesta", creada.getNombre());
    }

    @Test
    void obtenerTareasPorClase_deberiaRetornarLista() {
        List<TareaDTO> tareas = tareaService.obtenerTareasPorClase(1); // ID de clase

        assertNotNull(tareas);
        assertFalse(tareas.isEmpty(), "La clase debería tener al menos una tarea asignada.");
    }

    @Test
    void actualizarTarea_deberiaActualizarCorrectamente() {
        TareaDTO creada = tareaService.crearTarea(TareaDTO.builder()
                .nombre("Tarea Inicial")
                .fechaInicioEntrega(LocalDateTime.now())
                .fechaFinEntrega(LocalDateTime.now().plusDays(2))
                .porcentajeNota(10)
                .notaObtenida(0)
                .estadoEntrega("Pendiente")
                .claseId(1)
                .build());

        TareaDTO actualizada = TareaDTO.builder()
                .nombre("Tarea Actualizada")
                .fechaInicioEntrega(LocalDateTime.now().plusDays(1))
                .fechaFinEntrega(LocalDateTime.now().plusDays(3))
                .porcentajeNota(15)
                .notaObtenida(12)
                .estadoEntrega("Entregada")
                .claseId(1)
                .build();

        TareaDTO resultado = tareaService.actualizarTarea(creada.getId(), actualizada);

        assertEquals("Tarea Actualizada", resultado.getNombre());
        assertEquals("Entregada", resultado.getEstadoEntrega());
    }

    @Test
    void eliminarTarea_deberiaEliminarCorrectamente() {
        TareaDTO creada = tareaService.crearTarea(TareaDTO.builder()
                .nombre("Tarea Temporal")
                .fechaInicioEntrega(LocalDateTime.now())
                .fechaFinEntrega(LocalDateTime.now().plusDays(1))
                .porcentajeNota(5)
                .notaObtenida(0)
                .estadoEntrega("Pendiente")
                .claseId(1)
                .build());

        tareaService.eliminarTarea(creada.getId());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            tareaService.actualizarTarea(creada.getId(), creada);
        });

        assertTrue(ex.getMessage().contains("Tarea no encontrada"));
    }

}
