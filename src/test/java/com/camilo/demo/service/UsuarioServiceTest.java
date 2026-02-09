package com.camilo.demo.service;

import com.camilo.demo.dto.UsuarioResponseDTO;
import com.camilo.demo.mapper.UsuarioMapper;
import com.camilo.demo.model.Usuario;
import com.camilo.demo.repository.UsuarioRepository;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository repo;

    @Mock
    private UsuarioMapper mapper;

    @InjectMocks
    private UsuarioService service;

    @Test
    void buscarPorNombre() {

        String nombre = "Camilo";
        String genero = "Masculino";
        String direccion = "Belen";
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre(nombre);
        usuario.setDireccion(direccion);
        usuario.setGenero(genero);

        UsuarioResponseDTO dto =
                new UsuarioResponseDTO(1L, nombre, "Belen", "Masculino");

        when(repo.findByNombre(nombre))
                .thenReturn(List.of(usuario));

        when(mapper.toResponseDTOList(any()))
                .thenReturn(List.of(dto));

        // Act (ejecutar)
        List<UsuarioResponseDTO> resultado =
                service.buscarPorNombre(nombre);

        // Assert (verificar)
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(nombre, resultado.get(0).getNombre());


        // Verify (esto es CLAVE)
        verify(repo, times(1)).findByNombre(nombre);
        verify(mapper, times(1)).toResponseDTOList(any());




    }

    @Test
    void buscarPorID() {
        // Arrange
        Long id = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(id);

        UsuarioResponseDTO dto =
                new UsuarioResponseDTO(id, "Camilo", "Belen", "Masculino");

        when(repo.findById(id))
                .thenReturn(Optional.of(usuario));

        when(mapper.toResponseDTO(usuario))
                .thenReturn(dto);

        // Act
        UsuarioResponseDTO resultado = service.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());

        // Verify
        verify(repo, times(1)).findById(id);
        verify(mapper, times(1)).toResponseDTO(usuario);
    }

    @Test
    void buscarPorGenero() {
        // Arrange
        String genero = "Masculino";

        Usuario usuario = new Usuario();
        usuario.setGenero(genero);

        UsuarioResponseDTO dto =
                new UsuarioResponseDTO(1L, "Camilo", "Belen", genero);

        when(repo.findByGenero(genero))
                .thenReturn(List.of(usuario));

        when(mapper.toResponseDTOList(any()))
                .thenReturn(List.of(dto));

        // Act
        List<UsuarioResponseDTO> resultado =
                service.buscarPorGenero(genero);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(genero, resultado.get(0).getGenero());

        // Verify
        verify(repo, times(1)).findByGenero(genero);
        verify(mapper, times(1)).toResponseDTOList(any());
    }
}

