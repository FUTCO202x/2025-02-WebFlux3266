activityMVC - Spring Boot + Gradle + H2 (Java 21)
================================================

How to run:
1. Open a terminal in the project root.
2. If you have the Gradle wrapper files, run:
   ./gradlew bootRun  (Linux/macOS) or gradlew.bat bootRun (Windows)
   Otherwise, use a locally installed Gradle:
   gradle bootRun

The API will be available at http://localhost:9000

Endpoints:
 - POST /api/usuarios       -> create user
 - GET  /api/usuarios       -> list users
 - GET  /api/usuarios/{id}  -> get user by id
 - GET  /api/usuarios/params?nombre=...&apellido=... -> params example

H2 Console:
 - http://localhost:9000/h2-console
   JDBC URL: jdbc:h2:mem:activitydb
   User: sa
   Password: (empty)
