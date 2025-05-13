package pe.edu.utp.gestion_clases_api.microservice_clases_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearCurso_retornaCursoCreado() throws Exception {
        // Arrange
        CursoDTO cursoRequest = CursoDTO.builder()
                .nombre("Estructura de Datos")
                .seccion("ED-1")
                .modalidad("Presencial")
                .zoomUrl("https://zoom.us/estructura-datos")
                .profesorId(1)
                .build();

        CursoDTO cursoResponse = CursoDTO.builder()
                .id(1)
                .nombre("Estructura de Datos")
                .seccion("ED-1")
                .modalidad("Presencial")
                .zoomUrl("https://zoom.us/estructura-datos")
                .profesorId(1)
                .build();

        Mockito.when(cursoService.crearCurso(any(CursoDTO.class))).thenReturn(cursoResponse);

        // Act & Assert
        mockMvc.perform(post("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cursoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Estructura de Datos"));
    }
}
