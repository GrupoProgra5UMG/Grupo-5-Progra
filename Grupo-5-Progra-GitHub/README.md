# Citas Médicas API

API REST desarrollada con Spring Boot, Spring Data JPA, MySQL, Spring Security y JWT.

## Requisitos

- Java 21
- Maven 3.9+
- MySQL 8+

## Ejecutar el proyecto

Desde la carpeta raíz:

```bash
mvn clean package
java -jar target/citas-medicas-api-1.0.0.jar
```

También se puede ejecutar directamente con Maven:

```bash
mvn spring-boot:run
```

## Base de datos

La aplicación espera por defecto una base llamada `citas_medicas_db` en MySQL.

Las credenciales se pueden configurar mediante variables de entorno:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `DDL_AUTO`
- `JWT_SECRET`
- `JWT_EXPIRATION_MS`
- `PORT`

Ejemplo:

```bash
export DB_USERNAME=root
export DB_PASSWORD=tu_clave
mvn spring-boot:run
```

> El proyecto no incluye el `.jar` ni la carpeta `target/` porque son archivos generados por Maven y no deben subirse al repositorio.

## Postman

Se incluye la colección:

`Citas Medicas API - Auth.postman_collection.json`
