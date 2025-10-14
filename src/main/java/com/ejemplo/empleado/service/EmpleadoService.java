package com.ejemplo.empleado.service;

import com.ejemplo.empleado.entity.Empleado;
import com.ejemplo.empleado.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    // Crear un nuevo empleado
    public Empleado crearEmpleado(Empleado empleado) {
        if (empleadoRepository.existsByEmail(empleado.getEmail())) {
            throw new IllegalArgumentException("Ya existe un empleado con el email: " + empleado.getEmail());
        }
        return empleadoRepository.save(empleado);
    }

    // Obtener todos los empleados
    @Transactional(readOnly = true)
    public List<Empleado> obtenerTodosEmpleados() {
        return empleadoRepository.findAll();
    }

    // Obtener empleado por ID
    @Transactional(readOnly = true)
    public Optional<Empleado> obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    // Actualizar empleado
    public Empleado actualizarEmpleado(Long id, Empleado empleadoActualizado) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));

        // Verificar si el email ya está en uso por otro empleado
        if (!empleadoExistente.getEmail().equals(empleadoActualizado.getEmail()) &&
            empleadoRepository.existsByEmail(empleadoActualizado.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso por otro empleado");
        }

        // Actualizar campos
        empleadoExistente.setNombre(empleadoActualizado.getNombre());
        empleadoExistente.setApellido(empleadoActualizado.getApellido());
        empleadoExistente.setCargo(empleadoActualizado.getCargo());
        empleadoExistente.setDepartamento(empleadoActualizado.getDepartamento());
        empleadoExistente.setSalario(empleadoActualizado.getSalario());
        empleadoExistente.setFechaIngreso(empleadoActualizado.getFechaIngreso());
        empleadoExistente.setEmail(empleadoActualizado.getEmail());

        return empleadoRepository.save(empleadoExistente);
    }

    // Eliminar empleado
    public void eliminarEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new IllegalArgumentException("Empleado no encontrado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    // Buscar por departamento
    @Transactional(readOnly = true)
    public List<Empleado> buscarPorDepartamento(String departamento) {
        return empleadoRepository.findByDepartamento(departamento);
    }

    // Buscar por cargo
    @Transactional(readOnly = true)
    public List<Empleado> buscarPorCargo(String cargo) {
        return empleadoRepository.findByCargo(cargo);
    }

    // Buscar por nombre o apellido
    @Transactional(readOnly = true)
    public List<Empleado> buscarPorNombreOApellido(String busqueda) {
        return empleadoRepository.buscarPorNombreOApellido(busqueda);
    }

    // Buscar por email
    @Transactional(readOnly = true)
    public Optional<Empleado> buscarPorEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }
}
