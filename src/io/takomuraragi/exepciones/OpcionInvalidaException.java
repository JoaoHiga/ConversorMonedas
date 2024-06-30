package io.takomuraragi.exepciones;

import java.util.InputMismatchException;

public class OpcionInvalidaException extends InputMismatchException {
    private String mensaje;
    public OpcionInvalidaException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
