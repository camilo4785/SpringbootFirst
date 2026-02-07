package com.camilo.demo.repository;

import com.camilo.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNombre(String nombre);
    List<Usuario> findByDireccion(String direccion);
    List<Usuario> findByGenero(String genero);
    Optional<Usuario> findById(Long id);

}
