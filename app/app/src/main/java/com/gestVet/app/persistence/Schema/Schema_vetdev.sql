USE vetdev;

CREATE TABLE identificacion (
    numero_identificacion BIGINT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE persona (
    persona_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    identificacion BIGINT UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion TEXT,
    FOREIGN KEY (identificacion) REFERENCES identificacion(numero_identificacion)
);

CREATE TABLE usuario(
	usuario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    persona_id BIGINT UNIQUE,
    FOREIGN KEY (persona_id) REFERENCES persona(persona_id)
);

CREATE TABLE privilegio (
    privilegio_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL
);

CREATE TABLE permiso (
    permiso_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL
);

CREATE TABLE privilegio_permiso (
    privilegio_permiso_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    privilegio_id BIGINT,
    permiso_id BIGINT,
    FOREIGN KEY (privilegio_id) REFERENCES privilegio(privilegio_id),
    FOREIGN KEY (permiso_id) REFERENCES permiso(permiso_id)
);

CREATE TABLE administrador (
    administrador_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT UNIQUE,
    privilegio_id BIGINT,
	FOREIGN KEY (privilegio_id) REFERENCES privilegio(privilegio_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id)
);

CREATE TABLE veterinario (
    veterinario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    especialidad VARCHAR(200) NOT NULL,
    tarjeta_profesional VARCHAR(30) NOT NULL,
    usuario_id BIGINT UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id)
);

CREATE TABLE horario (
    horario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    horaInicio TIMESTAMP NOT NULL,
    horaFin TIMESTAMP NOT NULL
);

CREATE TABLE veterinario_horario (
    veterinario_horario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    veterinario_id BIGINT,
    horario_id BIGINT,
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(veterinario_id),
    FOREIGN KEY (horario_id) REFERENCES horario(horario_id)
);

CREATE TABLE cliente (
    cliente_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    persona_id BIGINT UNIQUE,
    es_propietario BOOLEAN NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES persona(persona_id)
);

CREATE TABLE propietario (
    propietario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT UNIQUE, 
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

CREATE TABLE historial_clinico (
    historial_clinico_id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE mascota (
    mascota_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    propietario_id BIGINT,
    historial_clinico_id BIGINT,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(150) NOT NULL,
    raza VARCHAR(150),
    sexo ENUM('Macho', 'Hembra') NOT NULL,
    fecha_nacimiento DATETIME,
    edad INT,
    peso DECIMAL(5,2),
    FOREIGN KEY (propietario_id) REFERENCES propietario(propietario_id),
    FOREIGN KEY (historial_clinico_id) REFERENCES historial_clinico(historial_clinico_id)
);


CREATE TABLE tipo_cita (
    tipo_cita_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL
);

CREATE TABLE cita (
    cita_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mascota_id BIGINT,
    horario_id BIGINT,
    veterinario_id BIGINT,
    fecha DATETIME NOT NULL,
    tipo_cita_id BIGINT,
    estado ENUM('Atendida', 'Confirmada', 'Cancelada') NOT NULL,
    FOREIGN KEY (mascota_id) REFERENCES mascota(mascota_id),
    FOREIGN KEY (horario_id) REFERENCES horario(horario_id),
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(veterinario_id),
    FOREIGN KEY (tipo_cita_id) REFERENCES tipo_cita(tipo_cita_id)
);

CREATE TABLE item_historial (
    item_historial_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    historial_clinico_id BIGINT,
    fecha DATE NOT NULL,
    diagnostico TEXT NOT NULL,
    tratamiento TEXT NOT NULL,
    observaciones TEXT,
    tipos VARCHAR(100),
    FOREIGN KEY (historial_clinico_id) REFERENCES historial_clinico(historial_clinico_id)
);

CREATE TABLE factura (
    factura_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total DOUBLE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    metodo_pago VARCHAR(60) NOT NULL,
    fecha_emision DATE NOT NULL
);

CREATE TABLE cliente_factura (
	cliente_factura_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,
    factura_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id),
    FOREIGN KEY (factura_id) REFERENCES factura(factura_id)
);


