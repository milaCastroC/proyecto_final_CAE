USE vetdev;

-- TABLA: persona
INSERT INTO persona (identificacion_tipo, identificacion, nombre, apellido, direccion, telefono, email) VALUES
('CC', '1001', 'Ana', 'Ramírez', 'Calle 1 #10-10', '3001112233', 'ana@gmail.com'),
('CC', '1002', 'Carlos', 'Gómez', 'Carrera 5 #20-50', '3102223344', 'carlos@gmail.com'),
('CC', '1003', 'Diana', 'López', 'Av 3 #33-21', '3013334455', 'diana@gmail.com'),
('CC', '1004', 'Pedro', 'Martínez', 'Calle 45 #12-34', '3124445566', 'pedro@gmail.com'),
('CC', '1005', 'Laura', 'Torres', 'Calle 50 #23-11', '3135556677', 'laura@gmail.com'),
('CC', '1006', 'Andrés', 'Pérez', 'Av 10 #45-32', '3146667788', 'andres@gmail.com'),
('CC', '1007', 'Sara', 'Mejía', 'Calle 80 #20-12', '3157778899', 'sara@gmail.com'),
('CC', '1008', 'Luis', 'Castaño', 'Carrera 22 #12-50', '3168889900', 'luis@gmail.com'),
('CC', '1009', 'Camila', 'Suárez', 'Av 7 #40-60', '3179990011', 'camila@gmail.com'),
('CC', '1010', 'Jorge', 'Quintero', 'Calle 12 #9-18', '3180001122', 'jorge@gmail.com');

-- TABLA: cliente
INSERT INTO cliente (persona_id, es_propietario) VALUES
(1, TRUE),
(2, TRUE),
(3, TRUE),
(4, TRUE),
(5, TRUE);

-- TABLA: usuario
INSERT INTO usuario (persona_id, username, password, rol) VALUES
(6, 'andres.p', 'pass123', 'admin'),
(7, 'sara.m', 'pass123', 'admin'),
(8, 'luis.c', 'pass123', 'veterinario'),
(9, 'camila.s', 'pass123', 'veterinario'),
(10, 'jorge.q', 'pass123', 'veterinario');

-- TABLA: administrador
INSERT INTO administrador (persona_id, cargo, area) VALUES
(6, 'Director', 'General'),
(7, 'Coordinadora', 'Finanzas');

-- TABLA: veterinario
INSERT INTO veterinario (persona_id, especialidad, tarjeta_profesional) VALUES
(8, 'Animales domésticos', 'TPV-001'),
(9, 'Animales exóticos', 'TPV-002'),
(10, 'Cirugía veterinaria', 'TPV-003');

-- TABLA: mascota
INSERT INTO mascota (propietario_id, nombre, especie, raza, sexo, fecha_nacimiento, edad, peso) VALUES
(1, 'Luna', 'Perro', 'Labrador', 'Hembra', '2020-01-01', 4, 25.5),
(2, 'Max', 'Gato', 'Siames', 'Macho', '2019-03-15', 5, 6.2),
(3, 'Rocky', 'Perro', 'Pitbull', 'Macho', '2021-07-10', 3, 30.0),
(4, 'Milo', 'Conejo', 'Mini lop', 'Macho', '2022-04-25', 2, 2.3),
(5, 'Nala', 'Gato', 'Persa', 'Hembra', '2018-11-20', 6, 4.8),
(1, 'Toby', 'Perro', 'Beagle', 'Macho', '2019-08-01', 5, 12.4),
(2, 'Lola', 'Gato', 'Maine Coon', 'Hembra', '2020-12-12', 4, 7.1),
(3, 'Coco', 'Ave', 'Loro', 'Macho', '2021-05-30', 3, 1.2),
(4, 'Kiara', 'Perro', 'Golden', 'Hembra', '2020-06-17', 4, 28.9),
(5, 'Simba', 'Gato', 'Bengala', 'Macho', '2017-09-05', 7, 5.6);

-- TABLA: factura
INSERT INTO factura (total, estado, metodo_pago, fecha_emision) VALUES
(120000, 'Pagada', 'Tarjeta', '2024-03-01'),
(80000, 'Pendiente', 'Efectivo', '2024-03-05'),
(60000, 'Pagada', 'Transferencia', '2024-03-10'),
(150000, 'Pagada', 'Efectivo', '2024-03-12'),
(95000, 'Pendiente', 'Tarjeta', '2024-03-15'),
(70000, 'Pagada', 'Efectivo', '2024-03-18'),
(110000, 'Pagada', 'Transferencia', '2024-03-20'),
(130000, 'Pendiente', 'Tarjeta', '2024-03-22'),
(50000, 'Pagada', 'Efectivo', '2024-03-25'),
(115000, 'Pagada', 'Tarjeta', '2024-03-28');

-- TABLA: cliente_factura
INSERT INTO cliente_factura (cliente_id, factura_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(1, 6),
(2, 7),
(3, 8),
(4, 9),
(5, 10);

-- TABLA: horario
INSERT INTO horario (hora_inicio, hora_fin) VALUES
('08:00:00', '09:00:00'),
('09:00:00', '10:00:00'),
('10:00:00', '11:00:00'),
('11:00:00', '12:00:00'),
('13:00:00', '14:00:00'),
('08:00:00', '09:00:00'),
('09:00:00', '10:00:00'),
('10:00:00', '11:00:00'),
('11:00:00', '12:00:00'),
('13:00:00', '14:00:00');

-- TABLA: veterinario_horario
INSERT INTO veterinario_horario (veterinario_id, horario_id, dia_semana) VALUES
(8, 1, 1),
(8, 2, 2),
(9, 3, 3),
(9, 4, 4),
(10, 5, 5),
(10, 6, 6),
(8, 7, 1),
(9, 8, 2),
(10, 9, 3),
(8, 10, 4);

-- TABLA: cita
INSERT INTO cita (mascota_id, horario_id, veterinario_id, fecha, tipo_cita, estado) VALUES
(1, 1, 8, '2024-04-01', 'Consulta general', 'Confirmada'),
(2, 2, 8, '2024-04-01', 'Vacunación', 'Atendida'),
(3, 3, 9, '2024-04-01', 'Revisión', 'Cancelada'),
(4, 4, 9, '2024-04-01', 'Consulta', 'Confirmada'),
(5, 5, 10, '2024-04-01', 'Desparasitación', 'Atendida'),
(6, 6, 10, '2024-04-02', 'Chequeo', 'Confirmada'),
(7, 7, 8, '2024-04-02', 'Control', 'Atendida'),
(8, 8, 9, '2024-04-02', 'Consulta', 'Confirmada'),
(9, 9, 10, '2024-04-02', 'Control de peso', 'Atendida'),
(10, 10, 8, '2024-04-02', 'Vacunación', 'Confirmada');

-- TABLA: item_historial
INSERT INTO item_historial (mascota_id, fecha, diagnostico, tratamiento, observaciones, tipo, cita_id) VALUES
(1, '2024-04-01', 'Otitis', 'Antibióticos', 'Buena respuesta', 'Consulta', 1),
(2, '2024-04-01', 'Vacuna anual', 'Aplicación vacuna', 'Sin novedad', 'Vacunación', 2),
(3, '2024-04-01', 'Dolor muscular', 'Analgésico', 'Debe seguirse', 'Revisión', 3),
(4, '2024-04-01', 'Corte de uñas', 'Hecho', 'Se portó bien', 'Consulta', 4),
(5, '2024-04-01', 'Parásitos', 'Desparasitación', 'Se recomienda control', 'Desparasitación', 5),
(6, '2024-04-02', 'Chequeo rutina', 'Ninguno', 'Buen estado', 'Chequeo', 6),
(7, '2024-04-02', 'Vacuna', 'Aplicación', 'Todo normal', 'Control', 7),
(8, '2024-04-02', 'Consulta general', 'Seguimiento', 'Observar evolución', 'Consulta', 8),
(9, '2024-04-02', 'Sobrepeso', 'Dieta', 'Control mensual', 'Control de peso', 9),
(10, '2024-04-02', 'Vacuna anual', 'Aplicación', 'Sin efectos adversos', 'Vacunación', 10);
