# 🌱 Ecomarket-SPA – Backend REST para Gestión de Productos

Backend académico desarrollado con **Java 17 y Spring Boot** para gestionar productos de un sistema de venta de productos ecológicos.

El proyecto utiliza una arquitectura por capas para separar responsabilidades entre controladores REST, lógica de negocio y acceso a datos.

## 🧩 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Web** para APIs REST
- **Spring Data JPA** para persistencia
- **MySQL** como base de datos
- **H2** para pruebas, cuando corresponde a la configuración del entorno
- **Maven** para gestión de dependencias y ciclo de vida
- **JUnit 5 / Mockito** para pruebas
- **OpenAPI / Swagger UI** para documentación de la API
- **Spring HATEOAS**

## 🏗️ Arquitectura

El backend está organizado mediante una arquitectura por capas:

```text
Cliente HTTP
     │
     ▼
Controllers REST
     │
     ▼
Services
     │
     ▼
Repositories
     │
     ▼
MySQL
```

### Componentes principales

- **Controller:** expone los endpoints HTTP y gestiona las solicitudes REST.
- **Service:** concentra la lógica de negocio.
- **Repository:** abstrae el acceso a datos mediante Spring Data JPA.
- **Model:** representa las entidades persistidas.
- **DTO:** permite validar y transportar datos de entrada de la API.
- **Exception Handler:** centraliza el manejo de errores y respuestas HTTP.

## 🚀 Funcionalidades

### Gestión de productos

La API permite:

- Crear productos.
- Consultar un producto por ID.
- Listar productos.
- Actualizar productos.
- Eliminar productos.
- Buscar productos por nombre.

Endpoint principal:

```text
/api/productos
```

## 🔎 Validación y manejo de errores

Las solicitudes de creación y actualización utilizan validación mediante `@Valid` y DTOs.

Los errores de validación responden con **400 Bad Request**, mientras que los recursos inexistentes responden con **404 Not Found**.

Los errores inesperados se manejan mediante un `@RestControllerAdvice`, evitando exponer detalles internos de la aplicación en la respuesta HTTP.

## 📚 Documentación de API

El proyecto integra **OpenAPI / Swagger UI** para facilitar la documentación y exploración de los endpoints REST.

## 🧪 Testing

El proyecto incorpora dependencias de **JUnit 5 y Mockito** para pruebas automatizadas de la aplicación y sus componentes.

## ☁️ Alcance del proyecto

Este repositorio corresponde a un proyecto académico enfocado en el desarrollo backend y la construcción de una API REST con Spring Boot.

El proyecto no representa actualmente una arquitectura completa de microservicios ni un despliegue productivo en la nube. Esos conceptos forman parte de otros proyectos del perfil y de la evolución académica del autor.

## 🎯 Objetivo de aprendizaje

El proyecto fue desarrollado para consolidar conocimientos en:

- Desarrollo de APIs REST.
- Arquitectura por capas.
- Inyección de dependencias.
- Persistencia con JPA.
- Validación de datos.
- Manejo centralizado de excepciones.
- Testing con JUnit y Mockito.
- Documentación de APIs mediante OpenAPI.
