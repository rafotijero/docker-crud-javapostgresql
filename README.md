# Sistema de Gestión de Empleados

Una aplicación empresarial completa desarrollada con Spring Boot para la gestión integral de empleados, incluyendo interfaz web con Thymeleaf, API RESTful, persistencia con PostgreSQL y despliegue con Docker.

## Características Principales

- **CRUD Completo**: Operaciones de Crear, Leer, Actualizar y Eliminar empleados
- **Interfaz Web**: UI responsive con Bootstrap 5 y Thymeleaf
- **API RESTful**: Endpoints completos para integración con otros sistemas
- **Base de Datos**: PostgreSQL con JPA/Hibernate
- **Validaciones**: Validación de datos con Jakarta Validation
- **Búsquedas Avanzadas**: Filtrado por departamento, cargo, nombre y email
- **Dockerizado**: Despliegue simplificado con Docker Compose
- **Datos de Prueba**: Base de datos inicializada con datos de ejemplo

## Estructura de la Entidad Empleado

- **ID**: Identificador único (auto-generado)
- **Nombre**: Nombre del empleado
- **Apellido**: Apellido del empleado
- **Cargo**: Puesto de trabajo
- **Departamento**: Departamento al que pertenece
- **Salario**: Salario del empleado
- **Fecha Ingreso**: Fecha de ingreso a la empresa
- **Email**: Correo electrónico (único)

## Requisitos Previos

- Docker Desktop instalado
- Java 17 o superior (para desarrollo local sin Docker)
- Maven 3.6+ (para desarrollo local sin Docker)

## Instalación y Ejecución

### Usando Docker Compose (Recomendado)

1. Clonar o descargar el proyecto

2. Desde el directorio del proyecto, ejecutar:
```bash
docker-compose up --build
```

3. Esperar a que los contenedores se inicien. La aplicación estará disponible en:
   - API: http://localhost:8080
   - PostgreSQL: localhost:5432

4. Para detener los contenedores:
```bash
docker-compose down
```

5. Para detener y eliminar volúmenes (datos):
```bash
docker-compose down -v
```

### Desarrollo Local (Sin Docker)

1. Instalar PostgreSQL y crear una base de datos llamada `empleados_db`

2. Actualizar `application.properties` con tus credenciales de PostgreSQL

3. Ejecutar:
```bash
mvn spring-boot:run
```

## Endpoints de la API

### Crear Empleado
```http
POST /api/empleados
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Pérez",
  "cargo": "Desarrollador",
  "departamento": "Tecnología",
  "salario": 5000.00,
  "fechaIngreso": "2024-01-15",
  "email": "juan.perez@ejemplo.com"
}
```

### Obtener Todos los Empleados
```http
GET /api/empleados
```

### Obtener Empleado por ID
```http
GET /api/empleados/{id}
```

### Actualizar Empleado
```http
PUT /api/empleados/{id}
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Pérez",
  "cargo": "Desarrollador Senior",
  "departamento": "Tecnología",
  "salario": 6000.00,
  "fechaIngreso": "2024-01-15",
  "email": "juan.perez@ejemplo.com"
}
```

### Eliminar Empleado
```http
DELETE /api/empleados/{id}
```

### Buscar por Departamento
```http
GET /api/empleados/departamento/{departamento}
```

### Buscar por Cargo
```http
GET /api/empleados/cargo/{cargo}
```

### Buscar por Nombre o Apellido
```http
GET /api/empleados/buscar?q=Juan
```

### Buscar por Email
```http
GET /api/empleados/email/{email}
```

## Ejemplos con cURL

### Crear un empleado:
```bash
curl -X POST http://localhost:8080/api/empleados \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ana",
    "apellido": "Martínez",
    "cargo": "Gerente",
    "departamento": "Administración",
    "salario": 7500.00,
    "fechaIngreso": "2023-06-01",
    "email": "ana.martinez@ejemplo.com"
  }'
```

### Obtener todos los empleados:
```bash
curl http://localhost:8080/api/empleados
```

### Actualizar un empleado:
```bash
curl -X PUT http://localhost:8080/api/empleados/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ana",
    "apellido": "Martínez",
    "cargo": "Gerente Senior",
    "departamento": "Administración",
    "salario": 8500.00,
    "fechaIngreso": "2023-06-01",
    "email": "ana.martinez@ejemplo.com"
  }'
```

### Eliminar un empleado:
```bash
curl -X DELETE http://localhost:8080/api/empleados/1
```

## Probar con Postman

Importa la siguiente colección o crea las peticiones manualmente:

1. Configura la URL base: `http://localhost:8080`
2. Usa los endpoints listados arriba
3. Para POST y PUT, usa el tipo de contenido `application/json`

## Configuración de la Base de Datos

Las credenciales por defecto son:
- **Base de datos**: empleados_db
- **Usuario**: admin
- **Contraseña**: admin123
- **Puerto**: 5432

Para cambiar estas credenciales, edita el archivo `docker-compose.yml`.

## Datos de Ejemplo

El archivo `init.sql` incluye 3 empleados de ejemplo que se cargan automáticamente al iniciar el contenedor de PostgreSQL.

## Tecnologías Utilizadas

### Backend
- **Spring Boot 3.2.0**: Framework principal de la aplicación
- **Spring Data JPA**: Capa de persistencia y acceso a datos
- **Spring Web**: Controladores REST y MVC
- **Thymeleaf**: Motor de plantillas para vistas HTML
- **Lombok**: Reducción de código boilerplate
- **Jakarta Validation**: Validación de beans

### Base de Datos
- **PostgreSQL 16**: Sistema de gestión de base de datos relacional
- **Hibernate**: ORM para mapeo objeto-relacional

### Frontend
- **Bootstrap 5.3.2**: Framework CSS para diseño responsive
- **Bootstrap Icons**: Librería de iconos
- **Thymeleaf**: Integración con Spring para páginas dinámicas

### DevOps & Tools
- **Docker**: Contenedorización de la aplicación
- **Docker Compose**: Orquestación de contenedores
- **Maven**: Gestión de dependencias y build

## Estructura del Proyecto

```
java-docker/
├── src/
│   ├── main/
│   │   ├── java/com/ejemplo/empleado/
│   │   │   ├── controller/
│   │   │   │   ├── EmpleadoController.java      # API REST endpoints
│   │   │   │   └── WebController.java           # Controlador web MVC
│   │   │   ├── entity/
│   │   │   │   └── Empleado.java                # Entidad JPA
│   │   │   ├── repository/
│   │   │   │   └── EmpleadoRepository.java      # Repositorio Spring Data
│   │   │   ├── service/
│   │   │   │   └── EmpleadoService.java         # Lógica de negocio
│   │   │   └── EmpleadoApplication.java         # Clase principal
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html                   # Página principal
│   │       │   └── empleados.html               # Gestión de empleados
│   │       └── application.properties           # Configuración
│   └── test/                                    # Tests unitarios
├── Dockerfile                                   # Construcción de imagen Docker
├── docker-compose.yml                           # Orquestación de contenedores
├── init.sql                                     # Script de inicialización DB
├── pom.xml                                      # Dependencias Maven
└── README.md                                    # Documentación
```

## Solución de Problemas

### Error de conexión a PostgreSQL
- Asegúrate de que Docker Desktop esté ejecutándose
- Verifica que el puerto 5432 no esté ocupado por otra instancia de PostgreSQL
- Espera unos segundos después de `docker-compose up` para que PostgreSQL se inicialice

### La aplicación no inicia
- Verifica los logs con: `docker-compose logs empleado-app`
- Asegúrate de que PostgreSQL esté saludable antes de que la app inicie

### Puerto 8080 ocupado
- Cambia el puerto en `docker-compose.yml`: `"8081:8080"`

## Acceso a la Aplicación

Una vez iniciada la aplicación, puedes acceder a través de:

### Interfaz Web
- **URL**: http://localhost:8080
- **Página Principal**: Página de bienvenida con acceso a gestión de empleados
- **Gestión de Empleados**: http://localhost:8080/empleados
  - Listar todos los empleados
  - Crear nuevos empleados
  - Editar empleados existentes
  - Eliminar empleados
  - Buscar por departamento o cargo

### API REST
- **Base URL**: http://localhost:8080/api/empleados
- **Formato**: JSON
- **Autenticación**: No requerida (para desarrollo)

## Arquitectura

La aplicación sigue el patrón de arquitectura en capas:

```
┌─────────────────────────────────────┐
│   Capa de Presentación              │
│   (Controllers: REST + Web MVC)     │
├─────────────────────────────────────┤
│   Capa de Negocio                   │
│   (Services)                        │
├─────────────────────────────────────┤
│   Capa de Persistencia              │
│   (Repositories + JPA)              │
├─────────────────────────────────────┤
│   Base de Datos                     │
│   (PostgreSQL)                      │
└─────────────────────────────────────┘
```

### Componentes Principales

1. **Controladores**:
   - `EmpleadoController`: Endpoints REST API
   - `WebController`: Páginas web con Thymeleaf

2. **Servicios**:
   - `EmpleadoService`: Lógica de negocio y validaciones

3. **Repositorios**:
   - `EmpleadoRepository`: Acceso a datos con Spring Data JPA

4. **Entidades**:
   - `Empleado`: Modelo de datos con validaciones

## Funcionalidades Detalladas

### Interfaz Web
- Formulario interactivo para crear/editar empleados
- Tabla responsive con listado de empleados
- Botones de acción para editar y eliminar
- Filtros por departamento y cargo
- Validación de formularios en frontend y backend
- Mensajes de confirmación y error

### API REST
- Endpoints RESTful siguiendo convenciones HTTP
- Códigos de estado apropiados (200, 201, 404, etc.)
- Validación de datos en requests
- Respuestas en formato JSON
- Manejo de errores y excepciones

