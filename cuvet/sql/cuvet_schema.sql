-- ============================================================
--  CUVET - Sistema de Gestión y Facturación Integral
--  Script COMPLETO: elimina y recrea la base desde cero
--  Ejecutar: mysql -u root -p < sql/cuvet_schema.sql
-- ============================================================

DROP DATABASE IF EXISTS cuvet_db;

CREATE DATABASE cuvet_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE cuvet_db;

SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------------
-- USUARIOS (contraseñas se insertan desde Java vía DatabaseSeeder)
-- ------------------------------------------------------------
CREATE TABLE usuarios (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    password_hash VARCHAR(100) NOT NULL,
    nombre        VARCHAR(100) NOT NULL,
    rol           ENUM('RECEPCIONISTA','VETERINARIO','ADMINISTRADOR') NOT NULL,
    activo        BOOLEAN      NOT NULL DEFAULT TRUE,
    creado_en     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------
-- CLIENTES
-- ------------------------------------------------------------
CREATE TABLE clientes (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(80)  NOT NULL,
    apellido   VARCHAR(80)  NOT NULL,
    dni        CHAR(8)      NOT NULL UNIQUE,
    telefono   VARCHAR(15),
    email      VARCHAR(120),
    creado_en  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------
-- MASCOTAS
-- ------------------------------------------------------------
CREATE TABLE mascotas (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(60) NOT NULL,
    especie    VARCHAR(40) NOT NULL,
    raza       VARCHAR(60),
    edad_meses INT         NOT NULL DEFAULT 0,
    sexo       CHAR(1)     NOT NULL CHECK (sexo IN ('M','F')),
    id_cliente INT         NOT NULL,
    CONSTRAINT fk_mascota_cliente
        FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------
-- SERVICIOS
-- ------------------------------------------------------------
CREATE TABLE servicios (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    tipo        ENUM('CONSULTA','CIRUGIA','VACUNA','MEDICAMENTO','HOSPITALIZACION','LABORATORIO') NOT NULL,
    descripcion VARCHAR(150) NOT NULL,
    precio      DECIMAL(10,2) NOT NULL,
    activo      BOOLEAN       NOT NULL DEFAULT TRUE
);

-- ------------------------------------------------------------
-- ATENCIONES
-- ------------------------------------------------------------
CREATE TABLE atenciones (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    fecha           DATE NOT NULL,
    id_cliente      INT  NOT NULL,
    id_mascota      INT  NOT NULL,
    id_veterinario  INT  NOT NULL,
    observaciones   TEXT,
    CONSTRAINT fk_atencion_cliente
        FOREIGN KEY (id_cliente)     REFERENCES clientes(id),
    CONSTRAINT fk_atencion_mascota
        FOREIGN KEY (id_mascota)     REFERENCES mascotas(id),
    CONSTRAINT fk_atencion_veterinario
        FOREIGN KEY (id_veterinario) REFERENCES usuarios(id)
);

CREATE TABLE atencion_servicios (
    id_atencion INT NOT NULL,
    id_servicio INT NOT NULL,
    PRIMARY KEY (id_atencion, id_servicio),
    CONSTRAINT fk_ats_atencion
        FOREIGN KEY (id_atencion) REFERENCES atenciones(id) ON DELETE CASCADE,
    CONSTRAINT fk_ats_servicio
        FOREIGN KEY (id_servicio) REFERENCES servicios(id)
);

-- ------------------------------------------------------------
-- FACTURAS
-- ------------------------------------------------------------
CREATE TABLE facturas (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    id_atencion INT          NOT NULL UNIQUE,
    numero      VARCHAR(20)  NOT NULL UNIQUE,
    fecha       DATE         NOT NULL,
    subtotal    DECIMAL(10,2) NOT NULL,
    igv         DECIMAL(10,2) NOT NULL,
    total       DECIMAL(10,2) NOT NULL,
    estado      ENUM('EMITIDA','ANULADA') NOT NULL DEFAULT 'EMITIDA',
    CONSTRAINT fk_factura_atencion
        FOREIGN KEY (id_atencion) REFERENCES atenciones(id)
);

CREATE TABLE factura_items (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    id_factura      INT NOT NULL,
    id_servicio     INT NOT NULL,
    cantidad        INT NOT NULL DEFAULT 1,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal        DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_item_factura
        FOREIGN KEY (id_factura)  REFERENCES facturas(id) ON DELETE CASCADE,
    CONSTRAINT fk_item_servicio
        FOREIGN KEY (id_servicio) REFERENCES servicios(id)
);

-- ------------------------------------------------------------
-- MEDICAMENTOS
-- ------------------------------------------------------------
CREATE TABLE medicamentos (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(100) NOT NULL,
    principio_activo VARCHAR(100),
    laboratorio      VARCHAR(80),
    stock_actual     INT          NOT NULL DEFAULT 0,
    stock_minimo     INT          NOT NULL DEFAULT 5,
    precio_venta     DECIMAL(10,2) NOT NULL,
    activo           BOOLEAN      NOT NULL DEFAULT TRUE
);

-- ------------------------------------------------------------
-- VACUNAS
-- ------------------------------------------------------------
CREATE TABLE vacunas (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    id_mascota       INT  NOT NULL,
    nombre_vacuna    VARCHAR(80) NOT NULL,
    lote             VARCHAR(40),
    fecha_aplicacion DATE NOT NULL,
    fecha_proxima    DATE,
    id_veterinario   INT  NOT NULL,
    CONSTRAINT fk_vacuna_mascota
        FOREIGN KEY (id_mascota)     REFERENCES mascotas(id),
    CONSTRAINT fk_vacuna_veterinario
        FOREIGN KEY (id_veterinario) REFERENCES usuarios(id)
);

-- ------------------------------------------------------------
-- CITAS
-- ------------------------------------------------------------
CREATE TABLE citas (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente     INT NOT NULL,
    id_mascota     INT NOT NULL,
    id_veterinario INT NOT NULL,
    fecha_hora     DATETIME NOT NULL,
    motivo         VARCHAR(200),
    estado         ENUM('PENDIENTE','CONFIRMADA','ATENDIDA','CANCELADA') NOT NULL DEFAULT 'PENDIENTE',
    CONSTRAINT fk_cita_cliente
        FOREIGN KEY (id_cliente)     REFERENCES clientes(id),
    CONSTRAINT fk_cita_mascota
        FOREIGN KEY (id_mascota)     REFERENCES mascotas(id),
    CONSTRAINT fk_cita_veterinario
        FOREIGN KEY (id_veterinario) REFERENCES usuarios(id)
);

-- ------------------------------------------------------------
-- LOG DE AUDITORÍA
-- ------------------------------------------------------------
CREATE TABLE log_auditoria (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario  INT NOT NULL,
    accion      ENUM('INSERT','UPDATE','DELETE','LOGIN','LOGOUT') NOT NULL,
    tabla       VARCHAR(50) NOT NULL,
    id_registro INT NOT NULL DEFAULT 0,
    timestamp   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    detalle     TEXT,
    CONSTRAINT fk_log_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

SET FOREIGN_KEY_CHECKS = 1;

-- ============================================================
-- DATOS DE PRUEBA (usuarios los crea DatabaseSeeder al iniciar)
-- ============================================================

INSERT INTO clientes (nombre, apellido, dni, telefono, email) VALUES
('María',  'García Torres',  '12345678', '999111222', 'maria.garcia@email.com'),
('Carlos', 'López Mendoza',  '87654321', '999333444', 'carlos.lopez@email.com'),
('Ana',    'Martínez Ruiz',  '11223344', '999555666', 'ana.martinez@email.com');

INSERT INTO mascotas (nombre, especie, raza, edad_meses, sexo, id_cliente) VALUES
('Luna',  'Perro', 'Labrador',      24, 'F', 1),
('Michi', 'Gato',  'Siamés',        18, 'M', 1),
('Rex',   'Perro', 'Pastor Alemán', 36, 'M', 2),
('Cleo',  'Gato',  'Persa',         12, 'F', 3);

INSERT INTO servicios (tipo, descripcion, precio) VALUES
('CONSULTA',        'Consulta médica general',             50.00),
('CONSULTA',        'Consulta de urgencia',                80.00),
('CIRUGIA',         'Cirugía de esterilización',          250.00),
('CIRUGIA',         'Cirugía ortopédica',                 450.00),
('VACUNA',          'Vacuna antirrábica',                  35.00),
('VACUNA',          'Vacuna polivalente canina',           45.00),
('MEDICAMENTO',     'Antibiótico Amoxicilina 500mg',       25.00),
('MEDICAMENTO',     'Antiparasitario externo',             30.00),
('HOSPITALIZACION', 'Hospitalización diaria',              80.00),
('LABORATORIO',     'Hemograma completo',                  60.00);

INSERT INTO medicamentos (nombre, principio_activo, laboratorio, stock_actual, stock_minimo, precio_venta) VALUES
('Amoxicilina 500mg',  'Amoxicilina',  'Bayer',  50, 10, 25.00),
('Ivermectina 1%',     'Ivermectina',  'MSD',    30,  5, 18.00),
('Carprofeno 50mg',    'Carprofeno',   'Pfizer', 20,  5, 35.00),
('Metronidazol 250mg', 'Metronidazol', 'Roche',   3,  5, 20.00);

-- ============================================================
-- FIN DEL SCRIPT
-- ============================================================
