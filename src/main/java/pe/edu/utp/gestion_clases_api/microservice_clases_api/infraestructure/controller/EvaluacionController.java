package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    // POST: Crear evaluación
    @PostMapping
    public ResponseEntity<EvaluacionDTO> crearEvaluacion(@RequestBody EvaluacionDTO evaluacionDTO) {
        EvaluacionDTO creada = evaluacionService.crearEvaluacion(evaluacionDTO);
        return ResponseEntity.ok(creada);
    }

    // GET: Listar evaluaciones por curso
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<EvaluacionDTO>> obtenerPorCurso(@PathVariable Integer cursoId) {
        return ResponseEntity.ok(evaluacionService.obtenerEvaluacionesPorCurso(cursoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> actualizarEvaluacion(@PathVariable Integer id,
            @RequestBody EvaluacionDTO dto) {
        EvaluacionDTO actualizado = evaluacionService.actualizarEvaluacion(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvaluacion(@PathVariable Integer id) {
        evaluacionService.eliminarEvaluacion(id);
        return ResponseEntity.noContent().build();
    }

}