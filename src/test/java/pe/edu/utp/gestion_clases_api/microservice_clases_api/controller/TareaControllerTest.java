package pe.edu.utp.gestion_clases_api.microservice_clases_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.TareaDTO;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.TareaService;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller.TareaController;

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

@WebMvcTest(TareaController.class)
public class TareaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TareaService tareaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearTarea_deberiaRetornarTareaCreada() throws Exception {
        TareaDTO request = TareaDTO.builder()
                .nombre("Informe Parcial")
                .fechaInicioEntrega(LocalDateTime.now())
                .fechaFinEntrega(LocalDateTime.now().plusDays(7))
                .porcentajeNota(20)
                .notaObtenida(0)
                .estadoEntrega("Pendiente")
                .claseId(1)
                .build();

        TareaDTO respuesta = request.toBuilder().id(1).build();

        Mockito.when(tareaService.crearTarea(any(TareaDTO.class))).thenReturn(respuesta);

        mockMvc.perform(post("/api/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Informe Parcial"));
    }

    @Test
    void obtenerTareasPorClase_deberiaRetornarLista() throws Exception {
        TareaDTO tarea = TareaDTO.builder()
                .id(1)
                .nombre("Informe Parcial")
                .fechaInicioEntrega(LocalDateTime.now())
                .fechaFinEntrega(LocalDateTime.now().plusDays(7))
                .porcentajeNota(20)
                .notaObtenida(0)
                .estadoEntrega("Pendiente")
                .claseId(1)
                .build();

        Mockito.when(tareaService.obtenerTareasPorClase(1)).thenReturn(Collections.singletonList(tarea));

        mockMvc.perform(get("/api/tareas/clase/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Informe Parcial"))
                .andExpect(jsonPath("$[0].estadoEntrega").value("Pendiente"));
    }
}
