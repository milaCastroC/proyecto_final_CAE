package com.gestVet.app.exceptions;

public class CitaNotAttendedException extends Exception {
    public CitaNotAttendedException() {
        super("La cita no está en estado 'Atendida'");
    }
}