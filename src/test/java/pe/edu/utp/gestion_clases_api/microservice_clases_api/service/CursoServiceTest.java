package pe.edu.utp.gestion_clases_api.microservice_clases_api.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.CursoDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.CursoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @Test
    void crearCurso_deberiaGuardarCorrectamente() {
        // Asegúrate de que el profesor con ID 1 exista antes de ejecutar esto
        CursoDTO dto = CursoDTO.builder()
                .nombre("Diseño de Base de Datos")
                .seccion("BBD1")
                .modalidad("Presencial")
                .zoomUrl("https://zoom.us/bbdd")
                .profesorId(1)
                .build();

        CursoDTO creado = cursoService.crearCurso(dto);

        assertNotNull(creado.getId());
        assertEquals("Diseño de Base de Datos", creado.getNombre());
    }

    @Test
    void actualizarCurso_deberiaActualizarCorrectamente() {
        // Crear primero
        CursoDTO creado = cursoService.crearCurso(CursoDTO.builder()
                .nombre("Curso Original")
                .seccion("A")
                .modalidad("Presencial")
                .zoomUrl("https://zoom.us/original")
                .profesorId(1)
                .build());

        // Modificar datos
        CursoDTO actualizado = CursoDTO.builder()
                .nombre("Curso Actualizado")
                .seccion("B")
                .modalidad("Virtual")
                .zoomUrl("https://zoom.us/actualizado")
                .profesorId(1)
                .build();

        CursoDTO resultado = cursoService.actualizarCurso(creado.getId(), actualizado);

        assertEquals("Curso Actualizado", resultado.getNombre());
        assertEquals("Virtual", resultado.getModalidad());
    }

    @Test
    void eliminarCurso_deberiaEliminarCorrectamente() {
        CursoDTO creado = cursoService.crearCurso(CursoDTO.builder()
                .nombre("Curso Eliminable")
                .seccion("E1")
                .modalidad("Presencial")
                .zoomUrl("https://zoom.us/eliminar")
                .profesorId(1)
                .build());

        cursoService.eliminarCurso(creado.getId());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            cursoService.obtenerCursoPorId(creado.getId());
        });

        assertTrue(ex.getMessage().contains("Curso no encontrado"));
    }

}
