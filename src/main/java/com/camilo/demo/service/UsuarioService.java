package com.camilo.demo.service;

import com.camilo.demo.model.Usuario;
import com.camilo.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> obtenerTodos() {
        return repo.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }

    public Usuario buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }
}
