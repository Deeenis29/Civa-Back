# Civa Backend

API REST desarrollada con Spring Boot para la gestión de buses y marcas.

---

## Tabla de Contenido

- [Características](#caracteristicas)
- [Tecnologías](#tecnologias)
- [Requisitos Previos](#requisitos-previos)
- [Configuración de la Base de Datos](#configuracion-de-la-base-de-datos)
- [Ejecución del Proyecto](#ejecucion-del-proyecto)
- [Autenticación](#autenticacion)
- [Endpoints Principales](#endpoints-principales)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Datos Iniciales](#datos-iniciales)

---

## Caracteristicas

- Gestión de entidades Bus y Brand (relación Bus ↔ Brand)
- Paginación de resultados
- Seguridad con Basic Auth
- Carga automática de datos iniciales

---

## Tecnologias

- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Spring Security
- Lombok
- Validation
- Spring Boot DevTools

---

## Requisitos Previos

- Java 21+
- PostgreSQL
- Maven o Gradle

---

## Configuracion de la Base de Datos

Agrega en `application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:postgresql://ep-quiet-breeze-acwdven4-pooler.sa-east-1.aws.neon.tech/neondb?sslmode=require&channel_binding=require
spring.datasource.username=neondb_owner
spring.datasource.password=npg_BvATZ65MNJgt
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

## Ejecucion del Proyecto

```bash
mvn spring-boot:run
```

---

## Autenticacion

Todos los endpoints están protegidos con Basic Authentication.

- **Usuario:** `admin`
- **Contraseña:** `admin123`

Ejemplo de header:

```
Authorization: Basic YWRtaW46YWRtaW4xMjM=
```

(`admin:admin123` codificado en Base64)

---

## Endpoints Principales

### API base
`http://localhost:8080`

### Obtener todos los buses (paginado)

**GET** `/api/buses?page=0&size=10`

**Respuesta ejemplo:**
```json
{
  "success": true,
  "message": "Lista de buses obtenida correctamente",
  "data": {
    "content": [],
    "totalElements": 27,
    "totalPages": 4,
    "size": 9,
    "number": 0
  }
}
```

### Obtener bus por ID

**GET** `/api/buses/{id}`

**Respuesta ejemplo:**
```json
{
  "success": true,
  "message": "Bus encontrado",
  "data": {}
}
```

### Ejemplo de error (paginación sin resultados)

```json
{
  "success": false,
  "message": "No hay resultados para la página solicitada",
  "data": {
    "content": [],
    "pageable": {}
  }
}
```

### Ejemplo de error (bus no encontrado)

```json
{
  "success": false,
  "message": "No se encontró el bus con id 28",
  "data": null
}
```

---

## Estructura del Proyecto

```
src/main/java/com/example/civabackend
│── controller     # Controladores REST (BusController)
│── config         # Configuraciones (DataLoader, Cors, Security)
│── dto            # DTOs para exponer datos
│── entity         # Entidades JPA (Bus, Brand)
│── payload        # ApiResponse (estructura estándar de respuesta)
│── repository     # Interfaces JPARepository
│── security       # Configuración de Spring Security y CORS
│── service        # Lógica de negocio (BusService)
```

---

## Datos Iniciales

Al iniciar el proyecto se generan automáticamente:
- 3 marcas: Volvo, Scania, Fiat
- 27 buses con numeración del 101 al 130 (ejemplo: ABC-101, ABC-102, ...)

---
