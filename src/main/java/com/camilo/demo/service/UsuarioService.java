package com.camilo.demo.service;
import com.camilo.demo.dto.UsuarioRequestDTO;
import com.camilo.demo.dto.UsuarioResponseDTO;
import com.camilo.demo.exception.DireccionNoDireccion;
import com.camilo.demo.exception.NoNombre;
import com.camilo.demo.exception.UsuarioNoEncontradoException;
import com.camilo.demo.mapper.UsuarioMapper;
import com.camilo.demo.model.Usuario;
import com.camilo.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repo, UsuarioMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<UsuarioResponseDTO> obtenerTodos() {

   //antes del mapper
    /*    return repo.findAll()
                .stream()
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getDireccion(),
                        usuario.getGenero()
                ))
                .toList();
*/
      //  List<Usuario> usuario = repo.findAll().stream().filter(usuario1 -> usuario1.getNombre().startsWith("A")).toList();
        return mapper.toResponseDTOList(repo.findAll()); //con el mapper
    }

    public UsuarioResponseDTO guardar(UsuarioRequestDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        Usuario guardado = repo.save(usuario);

        return mapper.toResponseDTO(guardado);

    }

    public List<UsuarioResponseDTO> buscarPorNombre(String nombre) {

        List<Usuario> usuarios = repo.findByNombre(nombre);

        usuarios.stream().filter(usuario -> usuario.getNombre().equals("Ana"));

        if (usuarios.isEmpty()) {
            throw new NoNombre();
        }

        return mapper.toResponseDTOList(usuarios); //con el mapper
    }

    public List<UsuarioResponseDTO> buscarPorDireccion(String direccion) {
        List<Usuario> usuarios = repo.findByDireccion(direccion);

        if (usuarios.isEmpty()) {
            throw new DireccionNoDireccion(direccion);
        }

        return mapper.toResponseDTOList(usuarios);
    }


    public List<UsuarioResponseDTO> buscarPorGenero(String genero) {
        List<Usuario> usuarios = repo.findByGenero(genero);

        if (usuarios.isEmpty()) {
            throw new DireccionNoDireccion(genero);
        }

        return mapper.toResponseDTOList(usuarios);
    }

    public UsuarioResponseDTO buscarPorId(Long id) {

        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getDireccion(),
                usuario.getGenero()
        );
    }




}
