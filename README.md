# 🌱 Ecomarket-SPA – Sistema de Venta de articulos  Ecológicos

Sistema desarrollado para apoyar la gestión de ventas de productos ecológicos . Utiliza arquitectura de microservicios con Spring Boot y una estructura escalable y mantenible.

## 🧩 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **Base de datos:** MySQL / H2
- **Maven**


## 🧱 Arquitectura

Este proyecto utiliza **arquitectura de microservicios**:

que serian los siguientes 

 
 1.productos-service/ 
# Gestión de productos

2.compras-service/ 
# Manejo de compras y pedidos

3.usuarios-service/
# Autenticación y roles

 4.config-server/
# Configuración centralizada

5.gateway/
# API Gateway para enrutar tráfico


Cada servicio se comunica a través de una API Gateway y está pensado para ser independiente y escalable.

✅ Funcionalidades
🛒 Registro y gestión de productos

🧾 Proceso de compras

🔐 Autenticación y autorización (Spring Security)

⚙️ Configuración centralizada

📦 Arquitectura desacoplada y escalable

