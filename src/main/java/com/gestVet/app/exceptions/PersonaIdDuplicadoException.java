package com.gestVet.app.exceptions;

public class PersonaIdDuplicadoException extends Exception {
    public PersonaIdDuplicadoException() {
        super("El ID de persona ya está asociado a otro cliente");
    }
    
}
