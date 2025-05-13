package pe.edu.utp.gestion_clases_api.microservice_clases_api.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.ProfesorDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.ProfesorService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProfesorServiceTest {

    @Autowired
    private ProfesorService profesorService;

    @Test
    void crearProfesor_deberiaGuardarCorrectamente() {
        ProfesorDTO dto = ProfesorDTO.builder()
                .nombre("Renzo Valdivia")
                .correoUniversitario("rvaldivia@utp.edu.pe")
                .build();

        ProfesorDTO creado = profesorService.crearProfesor(dto);

        assertNotNull(creado.getId());
        assertEquals("Renzo Valdivia", creado.getNombre());
    }

    @Test
    void listarProfesores_deberiaRetornarListaNoVacia() {
        List<ProfesorDTO> profesores = profesorService.listarProfesores();

        assertNotNull(profesores);
        assertFalse(profesores.isEmpty(), "Debe haber al menos un profesor registrado.");
    }

    @Test
    void actualizarProfesor_deberiaActualizarCorrectamente() {
        ProfesorDTO creado = profesorService.crearProfesor(ProfesorDTO.builder()
                .nombre("Carlos García")
                .correoUniversitario("cgarcia@utp.edu.pe")
                .build());

        ProfesorDTO actualizado = ProfesorDTO.builder()
                .nombre("Carlos G. Actualizado")
                .correoUniversitario("cg.actualizado@utp.edu.pe")
                .build();

        ProfesorDTO resultado = profesorService.actualizarProfesor(creado.getId(), actualizado);

        assertEquals("Carlos G. Actualizado", resultado.getNombre());
        assertEquals("cg.actualizado@utp.edu.pe", resultado.getCorreoUniversitario());
    }

    @Test
    void eliminarProfesor_deberiaEliminarCorrectamente() {
        ProfesorDTO creado = profesorService.crearProfesor(ProfesorDTO.builder()
                .nombre("Profesor Temporal")
                .correoUniversitario("temp@utp.edu.pe")
                .build());

        profesorService.eliminarProfesor(creado.getId());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            profesorService.actualizarProfesor(creado.getId(), creado);
        });

        assertTrue(ex.getMessage().contains("Profesor no encontrado"));
    }

}
