package com.camilo.demo.repository;

import com.camilo.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    Usuario findByNombre(String nombre);
    Usuario findByDireccion(String direccion);
    Optional<Usuario> findById(Long id);

}
