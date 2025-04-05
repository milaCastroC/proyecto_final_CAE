package com.gestVet.app.exceptions;

public class ItemHistorialNotFoundException extends RuntimeException {
    public ItemHistorialNotFoundException() {
        super("Ítem de historial no encontrado");
    }

    public ItemHistorialNotFoundException(String message) {
        super(message);
    }
}