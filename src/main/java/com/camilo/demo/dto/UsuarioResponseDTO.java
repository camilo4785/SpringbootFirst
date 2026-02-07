package com.camilo.demo.dto;

public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String genero;

    public UsuarioResponseDTO(Long id, String nombre, String direccion, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getGenero() {
        return genero;
    }

}
