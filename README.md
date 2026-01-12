# user-demo-mongodb

Aplicación demo en Spring Boot + MongoDB para gestionar usuarios.

## Resumen
Pequeña API REST que permite crear, listar, buscar y eliminar usuarios almacenados en MongoDB.

## Funciones de la aplicación

- Gestión completa de usuarios: crear, listar, buscar por username, obtener por id y eliminar.
- Validación de entrada en los endpoints (username obligatorio, password mínimo, email válido, roles obligatorios).
- Manejo centralizado de errores con respuestas consistentes: 400 (validación), 404 (no encontrado), 409 (usuario duplicado), 500 (errores generales).
- Persistencia en MongoDB usando Spring Data MongoDB.

## Endpoints

- GET /api/users  — devuelve lista con todos los usuarios.
- GET /api/users/{id} — devuelve un usuario por su id (404 si no existe).
- GET /api/users/find?username={username} — busca por username (404 si no existe).
- POST /api/users — crea un usuario. Valida el body (username,password,email,roles). Retorna 201 creado o 409 si el username ya existe.
- DELETE /api/users/{id} — elimina un usuario por id; 204 si se elimina correctamente.

## Flujo de la operación principal (crear usuario)

1. El cliente envía un POST con `UserRequest` (JSON) al endpoint `/api/users`.
2. Spring valida el request (anotaciones `@NotBlank`, `@Email`, `@Length`, `@NotEmpty`). Si hay errores → 400 con detalles por campo.
3. `UserController` delega en `UserService`:
   - `Usermapper` convierte `UserRequest` a `User` (documento).
   - `UserService` verifica existencia por username. Si ya existe lanza `UserAlreadyExistException` (se mapea a 409).
   - Si no existe, se guarda el documento con `UserRepository` y se devuelve un `UserResponse`.
4. El controlador devuelve 201 CREATED con el `UserResponse`.

## Manejo de errores (resumen)

- `GlobalExceptionHandler` unifica las respuestas de error con la clase `ApiErrorResponse`.
- Códigos utilizados:
  - 400 Bad Request — validación de campos.
  - 404 Not Found — recurso no encontrado.
  - 409 Conflict — usuario duplicado.
  - 500 Internal Server Error — cualquier otra excepción.

Ejemplo de respuesta de error (usuario duplicado):

```json
{
  "message": "el nombre de usuario ya existe",
  "code": 409,
  "errors": null
}
```

## Modelo de datos (resumen)

- `User` (documento MongoDB): id, username, email, password, roles (enum `Role`), configuration (avatar, colorTheme, active).
- DTOs:
  - `UserRequest` — lo que recibe la API para crear usuarios.
  - `UserResponse` — lo que devuelve la API (deberías evitar exponer passwords en producción).

## Ejemplos de uso (curl)

Listar usuarios:
```bat
curl -v http://127.0.0.1:8080/api/users
```

Crear usuario (puede devolver 409 si ya existe):
```bat
curl -4 -v -X POST http://127.0.0.1:8080/api/users -H "Content-Type: application/json" -d "{\"username\":\"andresquintero\",\"password\":\"xxxxxxxx\",\"email\":\"andresquinter2273@gmail.com\",\"roles\":[\"USER\",\"ADMIN\"]}"
```

Eliminar usuario (por id):
```bat
curl -v -X DELETE http://127.0.0.1:8080/api/users/<id>
```

## Requisitos y ejecución

- JDK 21
- Maven (wrapper `mvnw` incluido)
- MongoDB corriendo localmente en `mongodb://localhost:27017` (usa la base `user-demo` por defecto)

Compilar:
```bat
cd /d "e:\Dev senior code\BASE DE DATOS\mongodb\user-demo-mongodb"
mvnw.cmd -DskipTests package
```

Ejecutar:
```bat
cd /d "e:\Dev senior code\BASE DE DATOS\mongodb\user-demo-mongodb\target"
java -jar user-demo-mongodb-0.0.1-SNAPSHOT.jar
```

## Notas de seguridad y producción

- Nunca almacenar contraseñas en texto plano. Usa BCrypt u otro algoritmo de hashing antes de persistir.
- Validar y sanitizar entradas cuando la API esté expuesta públicamente.


