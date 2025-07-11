# 🌱 Ecomarket-SPA – Sistema Backend para Gestión de Venta de Productos Ecológicos

Este proyecto backend está diseñado para apoyar la gestión de ventas de productos ecológicos, implementado bajo una arquitectura de microservicios con Spring Boot. Su estructura modular garantiza escalabilidad, mantenibilidad y fácil extensión.

## 🧩 Tecnologías Utilizadas

- **Java 17**  
- **Spring Boot 3**  
  - Spring Web  
  - Spring Data JPA  
  - Spring Security  
- **Base de datos:** MySQL / H2 (entorno de pruebas)  
- **Maven** para gestión de dependencias y ciclo de vida del proyecto  

## 🧱 Arquitectura de Microservicios

El sistema está compuesto por los siguientes microservicios independientes y escalables, comunicándose a través de una API Gateway:

1. **productos-service**  
   - Gestión integral de productos: creación, actualización, eliminación y consulta.

2. **compras-service**  
   - Manejo de compras y pedidos, procesamiento de transacciones.

3. **usuarios-service**  
   - Autenticación, autorización y gestión de roles, implementado con Spring Security.

4. **config-server**  
   - Configuración centralizada para los distintos microservicios, facilitando la gestión de parámetros.

5. **gateway**  
   - API Gateway encargado de enrutar el tráfico y unificar el acceso a los servicios.

## Nota sobre despliegue

Este proyecto está diseñado para ser fácilmente desplegado en entornos productivos y en la nube, sin embargo, debido a los alcances establecidos en la pauta de evaluación, la etapa de despliegue en la nube no fue implementada ni evaluada en este trabajo.

## ✅ Funcionalidades Principales

- Gestión completa de productos con operaciones CRUD.  
- Proceso de compras optimizado con control de stock y pedidos.  
- Seguridad robusta mediante autenticación y autorización.  
- Configuración centralizada para mayor flexibilidad.  
- Arquitectura desacoplada que permite escalabilidad y mantenimiento sencillo.

---

Este backend está preparado para integrarse en entornos fullstack, facilitando la expansión y adaptación según necesidades futuras.  
