# Usar imagen oficial de Maven con JDK 21 para construir
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar el código fuente y archivos de configuración
COPY . .

# Construir el proyecto y empaquetar el JAR
RUN mvn clean package -DskipTests

# Usar una imagen ligera de Java 21 para producción
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el JAR desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto (ajusta si usas otro)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
