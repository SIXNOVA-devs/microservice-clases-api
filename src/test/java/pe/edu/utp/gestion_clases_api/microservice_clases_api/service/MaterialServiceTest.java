package pe.edu.utp.gestion_clases_api.microservice_clases_api.service;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.MaterialDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.MaterialService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MaterialServiceTest {

    @Autowired
    private MaterialService materialService;

    @Test
    void subirMaterial_deberiaGuardarCorrectamente() {
        // Asegúrate de que la clase con ID 1 exista
        MaterialDTO dto = MaterialDTO.builder()
                .titulo("Sesión 4 - Estrategias")
                .tipo("PDF")
                .urlVisualizacion("https://miapp.com/visualizar/s4.pdf")
                .urlDescarga("https://miapp.com/descargar/s4.pdf")
                .fechaSubida(LocalDate.now())
                .claseId(1)
                .build();

        MaterialDTO creado = materialService.subirMaterial(dto);

        assertNotNull(creado.getId());
        assertEquals("PDF", creado.getTipo());
    }

    @Test
    void obtenerMaterialPorClase_deberiaRetornarLista() {
        List<MaterialDTO> materiales = materialService.obtenerMaterialPorClase(1); // ID de clase

        assertNotNull(materiales);
        assertFalse(materiales.isEmpty(), "La clase debería tener al menos un material cargado.");
    }

    @Test
    void actualizarMaterial_deberiaActualizarCorrectamente() {
        MaterialDTO creado = materialService.subirMaterial(MaterialDTO.builder()
                .titulo("Semana 3 - Teoría")
                .tipo("PDF")
                .urlVisualizacion("https://miapp.com/visualizar/s3.pdf")
                .urlDescarga("https://miapp.com/descargar/s3.pdf")
                .fechaSubida(LocalDate.now())
                .claseId(1)
                .build());

        MaterialDTO actualizado = MaterialDTO.builder()
                .titulo("Semana 3 - Actualizado")
                .tipo("Video")
                .urlVisualizacion("https://miapp.com/visualizar/s3-video")
                .urlDescarga("https://miapp.com/descargar/s3-video")
                .fechaSubida(LocalDate.now().plusDays(1))
                .claseId(1)
                .build();

        MaterialDTO resultado = materialService.actualizarMaterial(creado.getId(), actualizado);

        assertEquals("Semana 3 - Actualizado", resultado.getTitulo());
        assertEquals("Video", resultado.getTipo());
    }

    @Test
    void eliminarMaterial_deberiaEliminarCorrectamente() {
        MaterialDTO creado = materialService.subirMaterial(MaterialDTO.builder()
                .titulo("PDF para eliminar")
                .tipo("PDF")
                .urlVisualizacion("https://miapp.com/visualizar/delete.pdf")
                .urlDescarga("https://miapp.com/descargar/delete.pdf")
                .fechaSubida(LocalDate.now())
                .claseId(1)
                .build());

        materialService.eliminarMaterial(creado.getId());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            materialService.actualizarMaterial(creado.getId(), creado);
        });

        assertTrue(ex.getMessage().contains("Material no encontrado"));
    }

}
