package pe.edu.utp.gestion_clases_api.microservice_clases_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.ClaseDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.ClaseService;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller.ClaseController;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClaseController.class)
public class ClaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClaseService claseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearClase_deberiaRetornarClaseCreada() throws Exception {
        ClaseDTO request = ClaseDTO.builder()
                .diaSemana("Lunes")
                .horaInicio(LocalTime.of(18, 0))
                .horaFin(LocalTime.of(20, 0))
                .semana(8)
                .ubicacion("Aula 302")
                .cursoId(1)
                .build();

        ClaseDTO respuesta = request.toBuilder().id(1).build();

        Mockito.when(claseService.crearClase(any(ClaseDTO.class))).thenReturn(respuesta);

        mockMvc.perform(post("/api/clases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.diaSemana").value("Lunes"));
    }

    @Test
    void obtenerClasesPorCurso_deberiaRetornarLista() throws Exception {
        ClaseDTO clase = ClaseDTO.builder()
                .id(1)
                .diaSemana("Lunes")
                .horaInicio(LocalTime.of(18, 0))
                .horaFin(LocalTime.of(20, 0))
                .semana(8)
                .ubicacion("Aula 302")
                .cursoId(1)
                .build();

        Mockito.when(claseService.obtenerClasesPorCurso(1)).thenReturn(Collections.singletonList(clase));

        mockMvc.perform(get("/api/clases/curso/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cursoId").value(1))
                .andExpect(jsonPath("$[0].ubicacion").value("Aula 302"));
    }
}
