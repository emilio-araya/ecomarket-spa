# 🌱 Ecomarket-SPA – Sistema de Venta para Feria Agroecológica

Sistema desarrollado para apoyar la gestión de ventas de productos agroecológicos en una feria local. Utiliza arquitectura de microservicios con Spring Boot y una estructura escalable y mantenible.

---

## 🧩 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **Base de datos:** MySQL / H2
- **Maven**
- **Docker** (opcional en futuras versiones)

---

## 🧱 Arquitectura

Este proyecto utiliza **arquitectura de microservicios** con los siguientes módulos:

ecomarket-spa/
├── productos-service/ # Gestión de productos
├── compras-service/ # Manejo de compras y pedidos
├── usuarios-service/ # Autenticación y roles
├── config-server/ # Configuración centralizada
└── gateway/ # API Gateway para enrutar tráfico



---

## 🚀 Cómo Ejecutar

1. Clonar el repositorio:
```bash
git clone https://github.com/emilio-araya/ecomarket-spa.git

Importar como proyecto Maven en tu IDE (IntelliJ, Eclipse, etc.)

Ejecutar cada microservicio desde su carpeta:


cd productos-service
mvn spring-boot:run

✅ Funcionalidades
🛒 Registro y gestión de productos

🧾 Proceso de compras

🔐 Autenticación y autorización (Spring Security)

⚙️ Configuración centralizada

📦 Arquitectura desacoplada y escalable
