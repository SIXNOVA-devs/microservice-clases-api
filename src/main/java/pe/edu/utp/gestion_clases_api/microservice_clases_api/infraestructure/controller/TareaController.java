package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    // POST: Crear tarea
    @PostMapping
    public ResponseEntity<TareaDTO> crearTarea(@RequestBody TareaDTO tareaDTO) {
        TareaDTO creada = tareaService.crearTarea(tareaDTO);
        return ResponseEntity.ok(creada);
    }

    // GET: Obtener tareas por clase
    @GetMapping("/clase/{claseId}")
    public ResponseEntity<List<TareaDTO>> obtenerPorClase(@PathVariable Integer claseId) {
        return ResponseEntity.ok(tareaService.obtenerTareasPorClase(claseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaDTO> actualizarTarea(@PathVariable Integer id, @RequestBody TareaDTO dto) {
        TareaDTO actualizado = tareaService.actualizarTarea(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Integer id) {
        tareaService.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }
}
