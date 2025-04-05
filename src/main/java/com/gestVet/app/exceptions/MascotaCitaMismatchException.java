package com.gestVet.app.exceptions;

public class MascotaCitaMismatchException extends Exception {
    public MascotaCitaMismatchException() {
        super("La mascota no coincide con la asociada a la cita");
    }
}