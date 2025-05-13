package pe.edu.utp.gestion_clases_api.microservice_clases_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.MaterialDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.MaterialService;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller.MaterialController;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MaterialController.class)
public class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialService materialService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void subirMaterial_deberiaRetornarMaterialCreado() throws Exception {
        MaterialDTO request = MaterialDTO.builder()
                .titulo("S05 - Algoritmos")
                .tipo("PDF")
                .urlVisualizacion("https://miapp.com/visualizar/s05.pdf")
                .urlDescarga("https://miapp.com/descargar/s05.pdf")
                .fechaSubida(LocalDate.now())
                .claseId(1)
                .build();

        MaterialDTO respuesta = request.toBuilder().id(1).build();

        Mockito.when(materialService.subirMaterial(any(MaterialDTO.class))).thenReturn(respuesta);

        mockMvc.perform(post("/api/materiales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipo").value("PDF"));
    }

    @Test
    void obtenerMaterialPorClase_deberiaRetornarLista() throws Exception {
        MaterialDTO material = MaterialDTO.builder()
                .id(1)
                .titulo("S05 - Algoritmos")
                .tipo("PDF")
                .urlVisualizacion("https://miapp.com/visualizar/s05.pdf")
                .urlDescarga("https://miapp.com/descargar/s05.pdf")
                .fechaSubida(LocalDate.now())
                .claseId(1)
                .build();

        Mockito.when(materialService.obtenerMaterialPorClase(1)).thenReturn(Collections.singletonList(material));

        mockMvc.perform(get("/api/materiales/clase/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("S05 - Algoritmos"))
                .andExpect(jsonPath("$[0].tipo").value("PDF"));
    }
}
