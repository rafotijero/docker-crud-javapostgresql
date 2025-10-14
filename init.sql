-- Script de inicialización de la base de datos (opcional)
-- Este script se ejecuta automáticamente cuando se crea el contenedor de PostgreSQL

-- Crear la tabla empleados si no existe (JPA lo hace automáticamente, pero este es un backup)
CREATE TABLE IF NOT EXISTS empleados (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    fecha_creacion DATE,
    fecha_actualizacion DATE
);

-- Insertar algunos datos de ejemplo (opcional)
INSERT INTO empleados (nombre, apellido, cargo, departamento, salario, fecha_ingreso, email, fecha_creacion, fecha_actualizacion)
VALUES
    ('Juan', 'Pérez', 'Desarrollador Senior', 'Tecnología', 5500.00, '2022-01-15', 'juan.perez@ejemplo.com', CURRENT_DATE, CURRENT_DATE),
    ('María', 'González', 'Gerente de Proyectos', 'Administración', 7000.00, '2021-06-10', 'maria.gonzalez@ejemplo.com', CURRENT_DATE, CURRENT_DATE),
    ('Carlos', 'Rodríguez', 'Analista de Datos', 'Tecnología', 4500.00, '2023-03-20', 'carlos.rodriguez@ejemplo.com', CURRENT_DATE, CURRENT_DATE)
ON CONFLICT (email) DO NOTHING;
