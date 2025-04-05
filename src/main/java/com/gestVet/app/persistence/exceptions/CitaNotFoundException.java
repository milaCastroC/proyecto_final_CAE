package com.gestVet.app.persistence.exceptions;

public class CitaNotFoundException extends Exception {
    public CitaNotFoundException() {
        super("Cita no encontrada");
    }
}
