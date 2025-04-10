package com.gestVet.app.exceptions;

public class VeterinarioNotFoundException extends RuntimeException {
    public VeterinarioNotFoundException() {
        super("Veterinario no encontrado");
    }
}
