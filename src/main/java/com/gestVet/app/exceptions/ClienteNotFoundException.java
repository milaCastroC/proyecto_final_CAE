package com.gestVet.app.exceptions;

public class ClienteNotFoundException extends Exception {
    public ClienteNotFoundException() {
        super("Cliente no encontrado");
    }

}
