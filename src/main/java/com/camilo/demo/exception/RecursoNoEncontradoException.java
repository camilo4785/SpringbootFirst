package com.camilo.demo.exception;

public abstract class RecursoNoEncontradoException extends RuntimeException {
    protected RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
