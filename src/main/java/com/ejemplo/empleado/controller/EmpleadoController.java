package com.ejemplo.empleado.controller;

import com.ejemplo.empleado.entity.Empleado;
import com.ejemplo.empleado.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    // Crear nuevo empleado
    @PostMapping
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody Empleado empleado) {
        try {
            Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Empleado creado exitosamente");
            response.put("empleado", nuevoEmpleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear el empleado: " + e.getMessage()));
        }
    }

    // Obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerTodosEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodosEmpleados();
        return ResponseEntity.ok(empleados);
    }

    // Obtener empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Long id) {
        return empleadoService.obtenerEmpleadoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    // Actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(
            @PathVariable Long id,
            @Valid @RequestBody Empleado empleado) {
        try {
            Empleado empleadoActualizado = empleadoService.actualizarEmpleado(id, empleado);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Empleado actualizado exitosamente");
            response.put("empleado", empleadoActualizado);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al actualizar el empleado: " + e.getMessage()));
        }
    }

    // Eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
        try {
            empleadoService.eliminarEmpleado(id);
            return ResponseEntity.ok(Map.of("mensaje", "Empleado eliminado exitosamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al eliminar el empleado: " + e.getMessage()));
        }
    }

    // Buscar por departamento
    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<Empleado>> buscarPorDepartamento(@PathVariable String departamento) {
        List<Empleado> empleados = empleadoService.buscarPorDepartamento(departamento);
        return ResponseEntity.ok(empleados);
    }

    // Buscar por cargo
    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<List<Empleado>> buscarPorCargo(@PathVariable String cargo) {
        List<Empleado> empleados = empleadoService.buscarPorCargo(cargo);
        return ResponseEntity.ok(empleados);
    }

    // Buscar por nombre o apellido
    @GetMapping("/buscar")
    public ResponseEntity<List<Empleado>> buscarPorNombreOApellido(@RequestParam String q) {
        List<Empleado> empleados = empleadoService.buscarPorNombreOApellido(q);
        return ResponseEntity.ok(empleados);
    }

    // Buscar por email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        return empleadoService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
}
