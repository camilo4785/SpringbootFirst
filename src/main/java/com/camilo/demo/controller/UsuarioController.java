package com.camilo.demo.controller;

import com.camilo.demo.model.Usuario;
import com.camilo.demo.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return service.obtenerTodos();
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario){
        return service.guardar(usuario);
    }

    @GetMapping("/buscar")
    public Usuario buscarUsuario(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }
}
