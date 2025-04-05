package com.gestVet.app.exceptions;

public class MascotaNotFoundException extends RuntimeException {
    public MascotaNotFoundException() {
        super("La mascota solicitada no existe");
    }
    
    public MascotaNotFoundException(String message) {
        super(message);
    }
}