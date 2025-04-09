-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS vetdev;
USE vetdev;

-- Tabla persona (Superclase)
CREATE TABLE persona (
    persona_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    identificacion_tipo VARCHAR(15) NOT NULL,
    identificacion VARCHAR(30) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    direccion TEXT,
    telefono VARCHAR(20),
    email VARCHAR(100),
    UNIQUE KEY (identificacion_tipo, identificacion)
);

-- Tabla cliente (Hereda de persona)
CREATE TABLE cliente (
    persona_id BIGINT PRIMARY KEY,
    es_propietario BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (persona_id) REFERENCES persona(persona_id) ON DELETE CASCADE
);

-- Tabla usuario (Hereda de persona)
CREATE TABLE usuario (
    persona_id BIGINT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES persona(persona_id) ON DELETE CASCADE
);

-- Tabla administrador (Hereda de usuario)
CREATE TABLE administrador (
    persona_id BIGINT PRIMARY KEY,
    cargo VARCHAR(50) NOT NULL,
    area VARCHAR(50) NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES usuario(persona_id) ON DELETE CASCADE
);

-- Tabla veterinario (Hereda de usuario)
CREATE TABLE veterinario (
    persona_id BIGINT PRIMARY KEY,
    especialidad VARCHAR(200) NOT NULL,
    tarjeta_profesional VARCHAR(100) NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES usuario(persona_id) ON DELETE CASCADE
);

-- Resto de tablas (ajustadas para usar persona_id)
CREATE TABLE mascota (
    mascota_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    propietario_id BIGINT NOT NULL,
    nombre VARCHAR(100),
    especie VARCHAR(50),
    raza VARCHAR(50),
    sexo ENUM('Macho', 'Hembra'),
    fecha_nacimiento DATETIME,
    edad INT,
    peso DECIMAL(5,2),
    FOREIGN KEY (propietario_id) REFERENCES cliente(persona_id) ON DELETE CASCADE,
    INDEX idx_mascota_propietario (propietario_id)
);

CREATE TABLE factura (
    factura_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total DOUBLE NOT NULL,
    estado VARCHAR(50),
    metodo_pago VARCHAR(50),
    fecha_emision DATE NOT NULL,
    INDEX idx_factura_fecha (fecha_emision)
);

CREATE TABLE cliente_factura (
    cliente_factura_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    factura_id BIGINT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(persona_id) ON DELETE CASCADE,
    FOREIGN KEY (factura_id) REFERENCES factura(factura_id) ON DELETE CASCADE,
    UNIQUE KEY idx_cliente_factura (cliente_id, factura_id)
);

CREATE TABLE horario (
    horario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    INDEX idx_horario (hora_inicio, hora_fin)
);

CREATE TABLE veterinario_horario (
    veterinario_horario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    veterinario_id BIGINT NOT NULL,
    horario_id BIGINT NOT NULL,
    dia_semana INT NOT NULL,
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(persona_id) ON DELETE CASCADE,
    FOREIGN KEY (horario_id) REFERENCES horario(horario_id) ON DELETE CASCADE
);

CREATE TABLE cita (
    cita_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mascota_id BIGINT NOT NULL,
    horario_id BIGINT NOT NULL,
    veterinario_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    tipo_cita VARCHAR(100),
    estado ENUM('Atendida', 'Confirmada', 'Cancelada') NOT NULL DEFAULT 'Confirmada',
    FOREIGN KEY (mascota_id) REFERENCES mascota(mascota_id) ON DELETE CASCADE,
    FOREIGN KEY (horario_id) REFERENCES horario(horario_id) ON DELETE CASCADE,
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(persona_id) ON DELETE CASCADE,
    INDEX idx_cita_fecha (fecha),
    INDEX idx_cita_mascota (mascota_id)
);

CREATE TABLE item_historial (
    item_historial_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mascota_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    diagnostico TEXT,
    tratamiento TEXT,
    observaciones TEXT,
    tipo VARCHAR(100),
    cita_id BIGINT,
    FOREIGN KEY (mascota_id) REFERENCES mascota(mascota_id) ON DELETE CASCADE,
    FOREIGN KEY (cita_id) REFERENCES cita(cita_id) ON DELETE SET NULL,
    INDEX idx_historial_mascota (mascota_id),
    INDEX idx_historial_fecha (fecha)
);

-- -- Creación de la base de datos
-- CREATE DATABASE IF NOT EXISTS vetdev;
-- USE vetdev;
--
-- -- Tabla persona
-- CREATE TABLE persona (
--    persona_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    identificacion_tipo VARCHAR(15) NOT NULL,
--    identificacion VARCHAR(30) NOT NULL,
--    nombre VARCHAR(100) NOT NULL,
--    apellido VARCHAR(100) NOT NULL,
--    direccion TEXT,
--    telefono VARCHAR(20),
--    email VARCHAR(100),
--    UNIQUE KEY (identificacion_tipo, identificacion)
-- );
--
-- -- Tabla usuario
-- CREATE TABLE usuario (
--    usuario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    username VARCHAR(50) NOT NULL UNIQUE,
--    password VARCHAR(255) NOT NULL,
--    rol VARCHAR(20) NOT NULL,
--    persona_id BIGINT UNIQUE,
--
--    UNIQUE KEY idx_username (username),
--    FOREIGN KEY (persona_id) REFERENCES persona(persona_id)
-- );
--
-- -- Tabla cliente
-- CREATE TABLE cliente (
--    cliente_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    persona_id BIGINT NOT NULL,
--    es_propietario BOOLEAN DEFAULT TRUE,
--    FOREIGN KEY (persona_id) REFERENCES persona(persona_id) ON DELETE CASCADE
-- );
--
-- -- Tabla veterinario
-- CREATE TABLE veterinario (
--    veterinario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    usuario_id BIGINT NOT NULL,
--    especialidad VARCHAR(200),
--    tarjeta_profesional VARCHAR(100),
--    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id) ON DELETE CASCADE
-- );
--
-- -- Tabla veterinario_horario
-- CREATE TABLE veterinario_horario (
--    veterinario_horario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    veterinario_id BIGINT NOT NULL,
--    horario_id BIGINT NOT NULL,
--    FOREIGN KEY (veterinario_id) REFERENCES veterinario(veterinario_id) ON DELETE CASCADE
-- );
--
-- -- Tabla horario
-- CREATE TABLE horario (
--    horario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    hora_inicio TIMESTAMP NOT NULL,
--    hora_fin TIMESTAMP NOT NULL,
--    INDEX idx_horario (hora_inicio, hora_fin)
-- );
--
-- -- Tabla administrador
-- CREATE TABLE administrador (
--    administrador_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    usuario_id BIGINT NOT NULL,
--    cargo VARCHAR(50),
--    area VARCHAR(50),
--    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id) ON DELETE CASCADE
-- );
--
-- -- Tabla mascota
-- CREATE TABLE mascota (
--    mascota_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    propietario_id BIGINT NOT NULL,
--    nombre VARCHAR(100),
--    especie VARCHAR(50),
--    raza VARCHAR(50),
--    sexo ENUM('Macho', 'Hembra'),
--    fecha_nacimiento DATETIME,
--    edad INT,
--    peso DECIMAL(5,2),
--    FOREIGN KEY (propietario_id) REFERENCES cliente(cliente_id) ON DELETE CASCADE,
--    INDEX idx_mascota_propietario (propietario_id)
-- );
--
-- -- Tabla factura
-- CREATE TABLE factura (
--    factura_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    total DOUBLE NOT NULL,
--    estado VARCHAR(50),
--    metodo_pago VARCHAR(50),
--    fecha_emision DATE NOT NULL,
--    INDEX idx_factura_fecha (fecha_emision)
-- );
--
-- -- Tabla cliente_factura
-- CREATE TABLE cliente_factura (
--    cliente_factura_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    cliente_id BIGINT NOT NULL,
--    factura_id BIGINT NOT NULL,
--    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id) ON DELETE CASCADE,
--    FOREIGN KEY (factura_id) REFERENCES factura(factura_id) ON DELETE CASCADE,
--    UNIQUE KEY idx_cliente_factura (cliente_id, factura_id)
-- );
--
-- -- Tabla cita
-- CREATE TABLE cita (
--    cita_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    mascota_id BIGINT NOT NULL,
--    horario_id BIGINT NOT NULL,
--    veterinario_id BIGINT NOT NULL,
--    fecha DATETIME NOT NULL,
--    tipo_cita VARCHAR(100),
--    estado ENUM('Atendida', 'Confirmada', 'Cancelada') NOT NULL DEFAULT 'Confirmada',
--    FOREIGN KEY (mascota_id) REFERENCES mascota(mascota_id) ON DELETE CASCADE,
--    FOREIGN KEY (horario_id) REFERENCES horario(horario_id) ON DELETE CASCADE,
--    FOREIGN KEY (veterinario_id) REFERENCES veterinario(veterinario_id) ON DELETE CASCADE,
--    INDEX idx_cita_fecha (fecha),
--    INDEX idx_cita_mascota (mascota_id)
-- );
--
-- -- Tabla item_historial
-- CREATE TABLE item_historial (
--    item_historial_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    mascota_id BIGINT NOT NULL,
--    fecha DATE NOT NULL,
--    diagnostico TEXT,
--    tratamiento TEXT,
--    observaciones TEXT,
--    tipo VARCHAR(100),
--    cita_id BIGINT,
--    FOREIGN KEY (mascota_id) REFERENCES mascota(mascota_id) ON DELETE CASCADE,
--    FOREIGN KEY (cita_id) REFERENCES cita(cita_id) ON DELETE SET NULL,
--    INDEX idx_historial_mascota (mascota_id),
--    INDEX idx_historial_fecha (fecha)
-- );
