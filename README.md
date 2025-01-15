# Foro Hub API

Foro Hub es una **API RESTful** desarrollada en **Java 17+** con **Spring Boot**, que permite gestionar tópicos de un foro. Este proyecto está diseñado para aprender y practicar conceptos como persistencia de datos, seguridad, y buenas prácticas en el desarrollo backend.

---

## Características

- CRUD completo para gestionar tópicos.
- Seguridad implementada con **JWT** y **Spring Security**.
- Documentación de la API con **SpringDoc OpenAPI**.
- Gestión de la base de datos con **Flyway** para migraciones.
- Persistencia utilizando **Spring Data JPA** con **MySQL**.

---

## Tecnologías

- **Lenguaje:** Java 17+
- **Frameworks:**
  - Spring Boot
  - Spring Data JPA
  - Spring Security
- **Base de Datos:** MySQL
- **Herramientas de documentación:** SpringDoc OpenAPI
- **Gestión de dependencias:** Maven

---

## Instalación y Configuración

### Prerrequisitos
- **Java 17+** instalado.
- **MySQL** configurado y corriendo.
- **Maven** instalado.

### Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/foro-hub.git
   cd foro-hub
   ```

2. Configura la base de datos en `application.properties` o `application.yml`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

3. Ejecuta las migraciones de Flyway:
   ```bash
   mvn flyway:migrate
   ```

4. Inicia la aplicación:
   ```bash
   mvn spring-boot:run
   ```

5. Accede a la API en `http://localhost:8080`.

---

## Endpoints Principales

| Método | Endpoint               | Descripción                           |
|--------|------------------------|---------------------------------------|
| GET    | `/api/topics`          | Lista todos los tópicos.             |
| POST   | `/api/topics`          | Crea un nuevo tópico.                |
| GET    | `/api/topics/{id}`     | Obtiene un tópico por su ID.         |
| PUT    | `/api/topics/{id}`     | Actualiza un tópico existente.       |
| DELETE | `/api/topics/{id}`     | Elimina un tópico.                   |

---

## Seguridad

- Implementación de **JSON Web Tokens (JWT)** para autenticar las solicitudes.
- Rutas protegidas requieren un token válido en el encabezado `Authorization`:
  ```http
  Authorization: Bearer <tu_token>
  ```

---

## Documentación

La documentación de la API está disponible en:

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## Autor

Desarrollado por **Alexis**. Principiante en el mundo de la programación.
