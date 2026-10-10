-- Inicialización de la base de datos de Ecomarket-SPA
-- Compatible con MariaDB

CREATE DATABASE IF NOT EXISTS db_ecomarket;
USE db_ecomarket;

CREATE TABLE IF NOT EXISTS proveedor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Datos de ejemplo
INSERT INTO proveedor (nombre, telefono, direccion) VALUES
('Proveedores S.A.', '123456789', 'Santiago'),
('Distribuciones XYZ', '987654321', 'Valparaíso'),
('Importadora Central', '555123456', 'Concepción');

INSERT INTO producto (nombre, descripcion, precio, stock, categoria) VALUES
('Bombilla LED', 'Bombilla LED de bajo consumo', 3500.00, 50, 'Electrónica'),
('Cortacésped', 'Cortacésped eléctrico para jardín', 85000.00, 10, 'Jardinería'),
('Camisa Casual', 'Camisa de algodón talla M', 12000.00, 25, 'Ropa'),
('Aspiradora', 'Aspiradora potente con bolsa', 45000.00, 15, 'Hogar'),
('Manguera de Riego', 'Manguera flexible para riego', 15000.00, 30, 'Jardinería');
