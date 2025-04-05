package com.gestVet.app.exceptions;

public class CitaNotFoundException extends Exception {
    public CitaNotFoundException() {
        super("Cita no encontrada");
    }
}
