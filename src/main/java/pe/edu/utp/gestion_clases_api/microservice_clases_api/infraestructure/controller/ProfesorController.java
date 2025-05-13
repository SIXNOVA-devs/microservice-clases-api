package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    // POST: Crear profesor
    @PostMapping
    public ResponseEntity<ProfesorDTO> crearProfesor(@RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO creado = profesorService.crearProfesor(profesorDTO);
        return ResponseEntity.ok(creado);
    }

    // GET: Listar profesores
    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> listarProfesores() {
        return ResponseEntity.ok(profesorService.listarProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> obtenerProfesorPorId(@PathVariable Integer id) {
        ProfesorDTO profesor = profesorService.obtenerProfesorPorId(id);
        return ResponseEntity.ok(profesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> actualizarProfesor(@PathVariable Integer id, @RequestBody ProfesorDTO dto) {
        ProfesorDTO actualizado = profesorService.actualizarProfesor(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable Integer id) {
        profesorService.eliminarProfesor(id);
        return ResponseEntity.noContent().build();
    }

}