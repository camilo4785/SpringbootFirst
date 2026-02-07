package com.camilo.demo.repository;

import com.camilo.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    Usuario findByNombre(String nombre);

}
