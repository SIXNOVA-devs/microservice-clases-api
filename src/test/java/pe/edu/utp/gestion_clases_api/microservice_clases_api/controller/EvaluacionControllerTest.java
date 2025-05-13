package pe.edu.utp.gestion_clases_api.microservice_clases_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.EvaluacionDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.EvaluacionService;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller.EvaluacionController;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EvaluacionController.class)
public class EvaluacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluacionService evaluacionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearEvaluacion_deberiaRetornarEvaluacionCreada() throws Exception {
        EvaluacionDTO request = EvaluacionDTO.builder()
                .titulo("Examen Parcial")
                .porcentajeNota(30)
                .fechaInicioEvaluacion(LocalDateTime.now().plusDays(2))
                .fechaFinEvaluacion(LocalDateTime.now().plusDays(3))
                .notaObtenida(0)
                .cursoId(1)
                .build();

        EvaluacionDTO respuesta = request.toBuilder().id(1).build();

        Mockito.when(evaluacionService.crearEvaluacion(any(EvaluacionDTO.class))).thenReturn(respuesta);

        mockMvc.perform(post("/api/evaluaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Examen Parcial"));
    }

    @Test
    void obtenerEvaluacionesPorCurso_deberiaRetornarLista() throws Exception {
        EvaluacionDTO evaluacion = EvaluacionDTO.builder()
                .id(1)
                .titulo("Examen Parcial")
                .porcentajeNota(30)
                .fechaInicioEvaluacion(LocalDateTime.now().plusDays(2))
                .fechaFinEvaluacion(LocalDateTime.now().plusDays(3))
                .notaObtenida(15)
                .cursoId(1)
                .build();

        Mockito.when(evaluacionService.obtenerEvaluacionesPorCurso(1))
                .thenReturn(Collections.singletonList(evaluacion));

        mockMvc.perform(get("/api/evaluaciones/curso/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Examen Parcial"))
                .andExpect(jsonPath("$[0].cursoId").value(1));
    }
}
