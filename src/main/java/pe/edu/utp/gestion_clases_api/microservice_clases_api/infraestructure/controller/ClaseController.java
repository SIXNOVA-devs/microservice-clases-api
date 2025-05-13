package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
@RequiredArgsConstructor
public class ClaseController {

    private final ClaseService claseService;

    // POST: Crear clase
    @PostMapping
    public ResponseEntity<ClaseDTO> crearClase(@RequestBody ClaseDTO claseDTO) {
        ClaseDTO creada = claseService.crearClase(claseDTO);
        return ResponseEntity.ok(creada);
    }

    // GET: Listar clases por ID de curso
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<ClaseDTO>> obtenerPorCurso(@PathVariable Integer cursoId) {
        return ResponseEntity.ok(claseService.obtenerClasesPorCurso(cursoId));
    }

    // GET: Listar clases por semana
    @GetMapping("/semana/{semana}")
    public ResponseEntity<List<ClaseDTO>> obtenerPorSemana(@PathVariable Integer semana) {
        return ResponseEntity.ok(claseService.obtenerClasesPorSemana(semana));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseDTO> actualizarClase(@PathVariable Integer id, @RequestBody ClaseDTO dto) {
        ClaseDTO actualizado = claseService.actualizarClase(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarClase(@PathVariable Integer id) {
        claseService.eliminarClase(id);
        return ResponseEntity.noContent().build();
    }
}
