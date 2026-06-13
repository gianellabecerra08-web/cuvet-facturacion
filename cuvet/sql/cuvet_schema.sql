-- ============================================================
--  CUVET - Sistema de Gestión y Facturación Integral
--  Clínica Veterinaria CUVET
--  Script de inicialización de la base de datos
--  Curso: Programación Orientada a Objetos II - UTP 2026-I
-- ============================================================

CREATE DATABASE IF NOT EXISTS cuvet_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE cuvet_db;

-- ------------------------------------------------------------
-- USUARIOS
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS usuarios (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(100) NOT NULL,       -- SHA-256 + salt en Base64
    nombre        VARCHAR(100) NOT NULL,
    rol           ENUM('RECEPCIONISTA','VETERINARIO','ADMINISTRADOR') NOT NULL,
    activo        BOOLEAN DEFAULT TRUE,
    creado_en     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Usuario administrador por defecto (contraseña: admin123)
INSERT INTO usuarios (username, password_hash, nombre, rol) VALUES
('admin', 'XohImNooBHFR0OnijDpYDIWr8NxLut+9T6zHiByZQoU=', 'Administrador CUVET', 'ADMINISTRADOR'),
('recep1', 'XohImNooBHFR0OniDpYDIWr8NxLut+9T6zHiByZQoU=', 'Recepcionista 1', 'RECEPCIONISTA'),
('vet1',   'XohImNooBHFR0OniDpYDIWr8NxLut+9T6zHiByZQoU=', 'Dr. Veterinario', 'VETERINARIO');

-- ------------------------------------------------------------
-- CLIENTES
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS clientes (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(80) NOT NULL,
    apellido   VARCHAR(80) NOT NULL,
    dni        CHAR(8) UNIQUE NOT NULL,
    telefono   VARCHAR(15),
    email      VARCHAR(120),
    creado_en  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Datos de prueba
INSERT INTO clientes (nombre, apellido, dni, telefono, email) VALUES
('María',  'García Torres',  '12345678', '999111222', 'maria.garcia@email.com'),
('Carlos', 'López Mendoza',  '87654321', '999333444', 'carlos.lopez@email.com'),
('Ana',    'Martínez Ruiz',  '11223344', '999555666', 'ana.martinez@email.com');

-- ------------------------------------------------------------
-- MASCOTAS
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS mascotas (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(60) NOT NULL,
    especie    VARCHAR(40) NOT NULL,
    raza       VARCHAR(60),
    edad_meses INT DEFAULT 0,
    sexo       CHAR(1) CHECK (sexo IN ('M','F')),
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE CASCADE
);

INSERT INTO mascotas (nombre, especie, raza, edad_meses, sexo, id_cliente) VALUES
('Luna',   'Perro', 'Labrador',     24, 'F', 1),
('Michi',  'Gato',  'Siamés',       18, 'M', 1),
('Rex',    'Perro', 'Pastor Alemán',36, 'M', 2),
('Cleo',   'Gato',  'Persa',        12, 'F', 3);

-- ------------------------------------------------------------
-- SERVICIOS
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS servicios (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    tipo        ENUM('CONSULTA','CIRUGIA','VACUNA','MEDICAMENTO','HOSPITALIZACION','LABORATORIO') NOT NULL,
    descripcion VARCHAR(150) NOT NULL,
    precio      DECIMAL(10,2) NOT NULL,
    activo      BOOLEAN DEFAULT TRUE
);

INSERT INTO servicios (tipo, descripcion, precio) VALUES
('CONSULTA',       'Consulta médica general',            50.00),
('CONSULTA',       'Consulta de urgencia',               80.00),
('CIRUGIA',        'Cirugía de esterilización',          250.00),
('CIRUGIA',        'Cirugía ortopédica',                 450.00),
('VACUNA',         'Vacuna antirrábica',                  35.00),
('VACUNA',         'Vacuna polivalente canina',           45.00),
('MEDICAMENTO',    'Antibiótico Amoxicilina 500mg',       25.00),
('MEDICAMENTO',    'Antiparasitario externo',             30.00),
('HOSPITALIZACION','Hospitalización diaria',              80.00),
('LABORATORIO',    'Hemograma completo',                  60.00);

-- ------------------------------------------------------------
-- ATENCIONES
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS atenciones (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    fecha           DATE NOT NULL,
    id_cliente      INT NOT NULL,
    id_mascota      INT NOT NULL,
    id_veterinario  INT NOT NULL,
    observaciones   TEXT,
    FOREIGN KEY (id_cliente)     REFERENCES clientes(id),
    FOREIGN KEY (id_mascota)     REFERENCES mascotas(id),
    FOREIGN KEY (id_veterinario) REFERENCES usuarios(id)
);

-- Tabla pivote atención ↔ servicios
CREATE TABLE IF NOT EXISTS atencion_servicios (
    id_atencion INT NOT NULL,
    id_servicio INT NOT NULL,
    PRIMARY KEY (id_atencion, id_servicio),
    FOREIGN KEY (id_atencion) REFERENCES atenciones(id) ON DELETE CASCADE,
    FOREIGN KEY (id_servicio) REFERENCES servicios(id)
);

-- ------------------------------------------------------------
-- FACTURAS
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS facturas (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    id_atencion INT NOT NULL UNIQUE,
    numero      VARCHAR(20) UNIQUE NOT NULL,
    fecha       DATE NOT NULL,
    subtotal    DECIMAL(10,2) NOT NULL,
    igv         DECIMAL(10,2) NOT NULL,
    total       DECIMAL(10,2) NOT NULL,
    estado      ENUM('EMITIDA','ANULADA') DEFAULT 'EMITIDA',
    FOREIGN KEY (id_atencion) REFERENCES atenciones(id)
);

CREATE TABLE IF NOT EXISTS factura_items (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    id_factura      INT NOT NULL,
    id_servicio     INT NOT NULL,
    cantidad        INT DEFAULT 1,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal        DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_factura)  REFERENCES facturas(id) ON DELETE CASCADE,
    FOREIGN KEY (id_servicio) REFERENCES servicios(id)
);

-- ------------------------------------------------------------
-- MEDICAMENTOS
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS medicamentos (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(100) NOT NULL,
    principio_activo VARCHAR(100),
    laboratorio      VARCHAR(80),
    stock_actual     INT DEFAULT 0,
    stock_minimo     INT DEFAULT 5,
    precio_venta     DECIMAL(10,2) NOT NULL,
    activo           BOOLEAN DEFAULT TRUE
);

INSERT INTO medicamentos (nombre, principio_activo, laboratorio, stock_actual, stock_minimo, precio_venta) VALUES
('Amoxicilina 500mg', 'Amoxicilina', 'Bayer', 50, 10, 25.00),
('Ivermectina 1%',    'Ivermectina', 'MSD',   30,  5, 18.00),
('Carprofeno 50mg',   'Carprofeno',  'Pfizer', 20,  5, 35.00),
('Metronidazol 250mg','Metronidazol','Roche',   3,  5, 20.00);  -- stock bajo

-- ------------------------------------------------------------
-- VACUNAS
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS vacunas (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    id_mascota       INT NOT NULL,
    nombre_vacuna    VARCHAR(80) NOT NULL,
    lote             VARCHAR(40),
    fecha_aplicacion DATE NOT NULL,
    fecha_proxima    DATE,
    id_veterinario   INT NOT NULL,
    FOREIGN KEY (id_mascota)     REFERENCES mascotas(id),
    FOREIGN KEY (id_veterinario) REFERENCES usuarios(id)
);

-- ------------------------------------------------------------
-- CITAS
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS citas (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente     INT NOT NULL,
    id_mascota     INT NOT NULL,
    id_veterinario INT NOT NULL,
    fecha_hora     DATETIME NOT NULL,
    motivo         VARCHAR(200),
    estado         ENUM('PENDIENTE','CONFIRMADA','ATENDIDA','CANCELADA') DEFAULT 'PENDIENTE',
    FOREIGN KEY (id_cliente)     REFERENCES clientes(id),
    FOREIGN KEY (id_mascota)     REFERENCES mascotas(id),
    FOREIGN KEY (id_veterinario) REFERENCES usuarios(id)
);

-- ------------------------------------------------------------
-- LOG DE AUDITORÍA
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS log_auditoria (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario  INT NOT NULL,
    accion      ENUM('INSERT','UPDATE','DELETE','LOGIN','LOGOUT') NOT NULL,
    tabla       VARCHAR(50) NOT NULL,
    id_registro INT DEFAULT 0,
    timestamp   DATETIME DEFAULT CURRENT_TIMESTAMP,
    detalle     TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

-- ============================================================
-- FIN DEL SCRIPT
-- ============================================================
