package com.camilo.demo.service;
import com.camilo.demo.dto.UsuarioRequestDTO;
import com.camilo.demo.dto.UsuarioResponseDTO;
import com.camilo.demo.exception.DireccionNoDireccion;
import com.camilo.demo.exception.UsuarioNoEncontradoException;
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

    public List<UsuarioResponseDTO> obtenerTodos() {

        return repo.findAll()
                .stream()
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getDireccion()
                ))
                .toList();

    }

    public UsuarioResponseDTO guardar(UsuarioRequestDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setDireccion(dto.getDireccion());

        Usuario guardado = repo.save(usuario);

        return new UsuarioResponseDTO(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getDireccion()
        );
    }

    public Usuario buscarPorNombre(String nombreCompleto) {
        return repo.findByNombre(nombreCompleto);
    }

    public List<UsuarioResponseDTO> buscarPorDireccion(String direccion) {
        List<UsuarioResponseDTO> resultado = repo.findAll()
                .stream()
                .filter(usuario ->
                        usuario.getDireccion() != null &&
                                usuario.getDireccion().equals(direccion)
                )
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getDireccion()
                ))
                .toList();

        if (resultado.isEmpty()) {
            throw new DireccionNoDireccion(direccion);
        }

        return resultado;
    }

    public UsuarioResponseDTO buscarPorId(Long id) {

        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getDireccion()
        );
    }




}
