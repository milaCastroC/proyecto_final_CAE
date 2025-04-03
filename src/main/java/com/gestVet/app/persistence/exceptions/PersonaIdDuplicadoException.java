package com.gestVet.app.persistence.exceptions;

public class PersonaIdDuplicadoException extends Exception {
    public PersonaIdDuplicadoException() {
        super("El ID de persona ya está asociado a otro cliente");
    }
    
}
