package com.ejemplo.empleado.repository;

import com.ejemplo.empleado.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Buscar por email
    Optional<Empleado> findByEmail(String email);

    // Buscar por departamento
    List<Empleado> findByDepartamento(String departamento);

    // Buscar por cargo
    List<Empleado> findByCargo(String cargo);

    // Buscar por nombre o apellido (búsqueda parcial)
    @Query("SELECT e FROM Empleado e WHERE " +
           "LOWER(e.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(e.apellido) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Empleado> buscarPorNombreOApellido(@Param("busqueda") String busqueda);

    // Verificar si existe un empleado con un email específico (excluyendo un ID)
    boolean existsByEmailAndIdNot(String email, Long id);

    // Verificar si existe un email
    boolean existsByEmail(String email);
}
