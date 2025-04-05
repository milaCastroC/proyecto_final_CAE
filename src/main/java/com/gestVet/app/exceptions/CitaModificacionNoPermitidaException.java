package com.gestVet.app.exceptions;

public class CitaModificacionNoPermitidaException extends Exception {
    public CitaModificacionNoPermitidaException() {
        super("No se pueden modificar campos además del estado");
    }
}
