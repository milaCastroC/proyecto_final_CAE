package com.gestVet.app.exceptions;

public class PropietarioNotFoundException extends RuntimeException {
    public PropietarioNotFoundException() {
        super("Propietario no encontrado");
    }
}
