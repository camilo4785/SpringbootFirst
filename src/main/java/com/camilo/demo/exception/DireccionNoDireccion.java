package com.camilo.demo.exception;

public class DireccionNoDireccion extends RecursoNoEncontradoException  {
    public DireccionNoDireccion(String direccion) {
        super("No se encontro " + direccion);
    }
}
