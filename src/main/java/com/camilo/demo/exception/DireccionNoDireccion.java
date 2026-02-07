package com.camilo.demo.exception;

public class DireccionNoDireccion extends RuntimeException {
    public DireccionNoDireccion(String direccion) {
        super("No se encontro " + direccion);
    }
}
