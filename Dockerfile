# Etapa 1: Build
FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar archivos de configuración de Maven
COPY pom.xml .

# Descargar dependencias (esto se cachea si pom.xml no cambia)
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/target/empleado-crud.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Variables de entorno (pueden ser sobrescritas por docker-compose)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/empleados_db
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=admin123

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
