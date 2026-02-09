package com.camilo.demo.service;

import com.camilo.demo.dto.UsuarioResponseDTO;
import com.camilo.demo.mapper.UsuarioMapper;
import com.camilo.demo.model.Usuario;
import com.camilo.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre(nombre);

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
}