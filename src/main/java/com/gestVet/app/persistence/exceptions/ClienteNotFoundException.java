package com.gestVet.app.persistence.exceptions;

public class ClienteNotFoundException extends Exception {
    public ClienteNotFoundException() {
        super("Cliente no encontrado");
    }

}
