package pe.edu.utp.gestion_clases_api.microservice_clases_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.ProfesorDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.ProfesorService;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller.ProfesorController;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfesorController.class)
public class ProfesorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfesorService profesorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearProfesor_deberiaRetornarProfesorCreado() throws Exception {
        ProfesorDTO request = ProfesorDTO.builder()
                .nombre("Laura Chávez")
                .correoUniversitario("lchavez@utp.edu.pe")
                .build();

        ProfesorDTO respuesta = request.toBuilder().id(1).build();

        Mockito.when(profesorService.crearProfesor(any(ProfesorDTO.class))).thenReturn(respuesta);

        mockMvc.perform(post("/api/profesores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Laura Chávez"));
    }

    @Test
    void listarProfesores_deberiaRetornarLista() throws Exception {
        ProfesorDTO profesor = ProfesorDTO.builder()
                .id(1)
                .nombre("Laura Chávez")
                .correoUniversitario("lchavez@utp.edu.pe")
                .build();

        Mockito.when(profesorService.listarProfesores()).thenReturn(Collections.singletonList(profesor));

        mockMvc.perform(get("/api/profesores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].correoUniversitario").value("lchavez@utp.edu.pe"));
    }


}

