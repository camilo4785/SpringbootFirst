package com.camilo.demo.exception;

public class NoNombre extends RecursoNoEncontradoException {
    public NoNombre (){
        super("Ningun nombre coincide con la busqueda");
    }

}
