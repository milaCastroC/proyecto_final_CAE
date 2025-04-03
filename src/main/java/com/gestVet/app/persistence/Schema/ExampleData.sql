USE vetdev;

INSERT INTO persona (identificacion_tipo, identificacion, nombre, apellido, telefono, email, direccion) VALUES
('CC', '12345678', 'Juan', 'Pérez', '555-1234', 'juan.perez@email.com', 'Calle 1 #10-20'),
('CC', '23456789', 'María', 'Gómez', '555-2345', 'maria.gomez@email.com', 'Carrera 5 #15-30'),
('Pasaporte', 'AB123456', 'Carlos', 'López', '555-3456', 'carlos.lopez@email.com', 'Avenida 3 #12-45'),
('CC', '34567890', 'Ana', 'Martínez', '555-4567', 'ana.martinez@email.com', 'Calle 8 #20-50'),
('CE', 'CE987654', 'Roberto', 'Rodríguez', '555-5678', 'roberto.rodriguez@email.com', 'Diagonal 7 #25-10'),
('CC', '45678901', 'Laura', 'Sánchez', '555-6789', 'laura.sanchez@email.com', 'Transversal 4 #30-15'),
('Pasaporte', 'CD456789', 'Miguel', 'Fernández', '555-7890', 'miguel.fernandez@email.com', 'Calle 2 #5-60'),
('CC', '56789012', 'Sofía', 'Díaz', '555-8901', 'sofia.diaz@email.com', 'Carrera 10 #40-70'),
('CE', 'CE654321', 'Pedro', 'Torres', '555-9012', 'pedro.torres@email.com', 'Avenida 6 #50-80'),
('CC', '67890123', 'Carmen', 'Ruiz', '555-0123', 'carmen.ruiz@email.com', 'Calle 11 #60-90'),
('CC', '78901234', 'Javier', 'Vargas', '555-1235', 'javier.vargas@email.com', 'Carrera 3 #70-100'),
('CC', '89012345', 'Lucía', 'Castro', '555-2346', 'lucia.castro@email.com', 'Avenida 9 #80-110'),
('Pasaporte', 'EF789012', 'Diego', 'Morales', '555-3457', 'diego.morales@email.com', 'Calle 5 #90-120'),
('CC', '90123456', 'Valentina', 'Ortega', '555-4568', 'valentina.ortega@email.com', 'Carrera 12 #100-130'),
('CE', 'CE456789', 'Fernando', 'Herrera', '555-5679', 'fernando.herrera@email.com', 'Transversal 8 #110-140'),
('CC', '01234567', 'Gabriela', 'Mendoza', '555-6780', 'gabriela.mendoza@email.com', 'Calle 14 #120-150'),
('Pasaporte', 'GH012345', 'Mateo', 'Silva', '555-7891', 'mateo.silva@email.com', 'Diagonal 5 #130-160'),
('CC', '11234568', 'Camila', 'Flores', '555-8902', 'camila.flores@email.com', 'Carrera 14 #140-170'),
('CE', 'CE123789', 'Ricardo', 'Navarro', '555-9013', 'ricardo.navarro@email.com', 'Calle 18 #150-180'),
('CC', '21345679', 'Isabel', 'Rojas', '555-0124', 'isabel.rojas@email.com', 'Avenida 15 #160-190');

-- Inserción de datos en la tabla usuario
INSERT INTO usuario (username, password, rol, persona_id) VALUES
('jperez', 'hash_password_123', 'VETERINARIO', 1),
('mgomez', 'hash_password_234', 'VETERINARIO', 2),
('clopez', 'hash_password_345', 'ADMINISTRADOR', 3),
('amartinez', 'hash_password_456', 'VETERINARIO', 4),
('rrodriguez', 'hash_password_567', 'RECEPCIONISTA', 5),
('lsanchez', 'hash_password_678', 'VETERINARIO', 6),
('mfernandez', 'hash_password_789', 'ADMINISTRADOR', 7),
('sdiaz', 'hash_password_890', 'VETERINARIO', 8),
('ptorres', 'hash_password_901', 'RECEPCIONISTA', 9),
('cruiz', 'hash_password_012', 'ADMINISTRADOR', 10);

-- Inserción de datos en la tabla cliente
INSERT INTO cliente (persona_id, es_propietario) VALUES
(1, TRUE),
(2, TRUE),
(4, TRUE),
(5, FALSE),
(6, TRUE),
(7, TRUE),
(11, TRUE),
(12, TRUE),
(13, TRUE),
(14, TRUE);

-- Inserción de datos en la tabla veterinario
INSERT INTO veterinario (usuario_id, especialidad, tarjeta_profesional) VALUES
(1, 'Medicina General', 'TP-12345'),
(2, 'Cirugía', 'TP-23456'),
(4, 'Dermatología', 'TP-34567'),
(6, 'Cardiología', 'TP-45678'),
(8, 'Ortopedia', 'TP-56789'),
(5, 'Medicina Interna', 'TP-67890'),
(9, 'Oftalmología', 'TP-78901'),
(3, 'Neurología', 'TP-89012'),
(7, 'Odontología', 'TP-90123'),
(10, 'Radiología', 'TP-01234');

-- Inserción de datos en la tabla horario
INSERT INTO horario (horaInicio, horaFin) VALUES
('2025-04-01 08:00:00', '2025-04-01 10:00:00'),
('2025-04-01 10:30:00', '2025-04-01 12:30:00'),
('2025-04-01 14:00:00', '2025-04-01 16:00:00'),
('2025-04-01 16:30:00', '2025-04-01 18:30:00'),
('2025-04-02 08:00:00', '2025-04-02 10:00:00'),
('2025-04-02 10:30:00', '2025-04-02 12:30:00'),
('2025-04-02 14:00:00', '2025-04-02 16:00:00'),
('2025-04-02 16:30:00', '2025-04-02 18:30:00'),
('2025-04-03 08:00:00', '2025-04-03 10:00:00'),
('2025-04-03 10:30:00', '2025-04-03 12:30:00');

-- Inserción de datos en la tabla veterinario_horario
INSERT INTO veterinario_horario (veterinario_id, horario_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(4, 7),
(4, 8),
(5, 9),
(5, 10);

-- Inserción de datos en la tabla administrador
INSERT INTO administrador (usuario_id, cargo, area) VALUES
(3, 'Gerente General', 'Administración'),
(7, 'Jefe de Personal', 'Recursos Humanos'),
(10, 'Coordinador', 'Logística'),
(5, 'Asistente', 'Recepción'),
(9, 'Supervisor', 'Contabilidad'),
(1, 'Auxiliar', 'Atención al Cliente'),
(2, 'Director', 'Marketing'),
(4, 'Gerente', 'Finanzas'),
(6, 'Analista', 'Sistemas'),
(8, 'Coordinador', 'Operaciones');

-- Inserción de datos en la tabla mascota
INSERT INTO mascota (propietario_id, nombre, especie, raza, sexo, fecha_nacimiento, edad, peso) VALUES
(1, 'Rocky', 'Perro', 'Labrador', 'Macho', '2020-05-15 00:00:00', 5, 28.5),
(2, 'Luna', 'Gato', 'Siamés', 'Hembra', '2019-03-10 00:00:00', 6, 4.2),
(3, 'Max', 'Perro', 'Golden Retriever', 'Macho', '2021-07-22 00:00:00', 4, 32.1),
(4, 'Michi', 'Gato', 'Persa', 'Hembra', '2022-01-05 00:00:00', 3, 3.8),
(5, 'Toby', 'Perro', 'Bulldog', 'Macho', '2018-11-30 00:00:00', 7, 15.3),
(6, 'Nala', 'Perro', 'Pastor Alemán', 'Hembra', '2020-09-18 00:00:00', 5, 30.2),
(7, 'Simba', 'Gato', 'Maine Coon', 'Macho', '2019-05-27 00:00:00', 6, 7.5),
(8, 'Bella', 'Perro', 'Chihuahua', 'Hembra', '2021-12-03 00:00:00', 3, 2.1),
(9, 'Zeus', 'Perro', 'Rottweiler', 'Macho', '2018-08-12 00:00:00', 7, 45.6),
(10, 'Kitty', 'Gato', 'Bengalí', 'Hembra', '2022-04-19 00:00:00', 3, 4.9);

-- Inserción de datos en la tabla factura
INSERT INTO factura (total, estado, metodo_pago, fecha_emision) VALUES
(150.75, 'PAGADA', 'Efectivo', '2025-03-01'),
(220.30, 'PAGADA', 'Tarjeta de Crédito', '2025-03-03'),
(85.50, 'PENDIENTE', 'Transferencia', '2025-03-05'),
(310.25, 'PAGADA', 'Tarjeta de Débito', '2025-03-07'),
(175.00, 'PAGADA', 'Efectivo', '2025-03-10'),
(95.80, 'PENDIENTE', 'Transferencia', '2025-03-12'),
(260.45, 'PAGADA', 'Tarjeta de Crédito', '2025-03-15'),
(120.60, 'PAGADA', 'Efectivo', '2025-03-18'),
(350.90, 'PENDIENTE', 'Transferencia', '2025-03-20'),
(195.25, 'PAGADA', 'Tarjeta de Débito', '2025-03-25');

-- Inserción de datos en la tabla cliente_factura
INSERT INTO cliente_factura (cliente_id, factura_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- Inserción de datos en la tabla cita
INSERT INTO cita (mascota_id, horario_id, veterinario_id, fecha, tipo_cita, estado) VALUES
(1, 1, 1, '2025-04-01 08:30:00', 'Vacunación', 'Confirmada'),
(2, 2, 2, '2025-04-01 11:00:00', 'Control', 'Confirmada'),
(3, 3, 3, '2025-04-01 14:30:00', 'Emergencia', 'Atendida'),
(4, 4, 4, '2025-04-01 17:00:00', 'Desparasitación', 'Confirmada'),
(5, 5, 5, '2025-04-02 09:00:00', 'Control', 'Cancelada'),
(6, 6, 1, '2025-04-02 11:30:00', 'Cirugía', 'Confirmada'),
(7, 7, 2, '2025-04-02 15:00:00', 'Vacunación', 'Confirmada'),
(8, 8, 3, '2025-04-02 17:30:00', 'Control', 'Confirmada'),
(9, 9, 4, '2025-04-03 09:30:00', 'Emergencia', 'Confirmada'),
(10, 10, 5, '2025-04-03 11:00:00', 'Desparasitación', 'Confirmada');

-- Inserción de datos en la tabla item_historial
INSERT INTO item_historial (mascota_id, fecha, diagnostico, tratamiento, observaciones, tipo, cita_id) VALUES
(1, '2025-04-01', 'Vacunación preventiva', 'Aplicación de vacuna contra rabia', 'Mascota en buen estado general', 'Vacunación', 1),
(2, '2025-04-01', 'Control rutinario', 'No requiere tratamiento', 'Se recomiendan vitaminas', 'Control', 2),
(3, '2025-04-01', 'Fractura en pata trasera', 'Colocación de yeso', 'Reposo por 3 semanas', 'Emergencia', 3),
(4, '2025-04-01', 'Parásitos intestinales', 'Administración de antiparasitario', 'Control en 15 días', 'Desparasitación', 4),
(5, '2025-03-20', 'Dermatitis alérgica', 'Tratamiento con crema tópica', 'Evitar contacto con alérgenos', 'Dermatología', NULL),
(6, '2025-03-15', 'Otitis externa', 'Gotas óticas antibióticas', 'Limpiar diariamente', 'Otorrinolaringología', NULL),
(7, '2025-03-10', 'Vacunación múltiple', 'Pentavalente canina', 'Próxima dosis en 3 semanas', 'Vacunación', NULL),
(8, '2025-03-05', 'Control dental', 'Limpieza dental completa', 'Buena salud bucal', 'Odontología', NULL),
(9, '2025-02-28', 'Corte profundo', 'Sutura y antibióticos', 'Retirar puntos en 10 días', 'Emergencia', NULL),
(10, '2025-02-25', 'Control de peso', 'Dieta especializada', 'Sobrepeso moderado', 'Nutrición', NULL);