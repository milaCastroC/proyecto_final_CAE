package com.gestVet.app.persistence.exceptions;

public class CitaModificacionNoPermitidaException extends Exception {
    public CitaModificacionNoPermitidaException() {
        super("No se pueden modificar campos además del estado");
    }
}
