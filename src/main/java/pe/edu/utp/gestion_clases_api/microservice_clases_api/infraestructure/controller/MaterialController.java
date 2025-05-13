package pe.edu.utp.gestion_clases_api.microservice_clases_api.infraestructure.controller;

import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.dtos.*;
import pe.edu.utp.gestion_clases_api.microservice_clases_api.aplication.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    // POST: Subir material
    @PostMapping
    public ResponseEntity<MaterialDTO> subirMaterial(@RequestBody MaterialDTO materialDTO) {
        MaterialDTO creado = materialService.subirMaterial(materialDTO);
        return ResponseEntity.ok(creado);
    }

    // GET: Listar materiales por clase
    @GetMapping("/clase/{claseId}")
    public ResponseEntity<List<MaterialDTO>> obtenerPorClase(@PathVariable Integer claseId) {
        return ResponseEntity.ok(materialService.obtenerMaterialPorClase(claseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> actualizarMaterial(@PathVariable Integer id, @RequestBody MaterialDTO dto) {
        MaterialDTO actualizado = materialService.actualizarMaterial(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMaterial(@PathVariable Integer id) {
        materialService.eliminarMaterial(id);
        return ResponseEntity.noContent().build();
    }

}