package io.takomuraragi.exepciones;

public class ConversionNoValidaException extends RuntimeException{
    private String mensaje;
    public ConversionNoValidaException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
