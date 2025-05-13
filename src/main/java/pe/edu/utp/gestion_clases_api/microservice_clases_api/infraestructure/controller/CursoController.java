package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    // POST: crear curso
    @PostMapping
    public ResponseEntity<CursoDTO> crearCurso(@RequestBody CursoDTO cursoDTO) {
        CursoDTO creado = cursoService.crearCurso(cursoDTO);
        return ResponseEntity.ok(creado);
    }

    // GET: listar todos los cursos
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    // GET: obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> obtenerCursoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(cursoService.obtenerCursoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> actualizarCurso(@PathVariable Integer id, @RequestBody CursoDTO dto) {
        CursoDTO actualizado = cursoService.actualizarCurso(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE: eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Integer id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }

}