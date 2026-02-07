package com.camilo.demo.mapper;


import com.camilo.demo.dto.UsuarioRequestDTO;
import com.camilo.demo.dto.UsuarioResponseDTO;
import com.camilo.demo.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toResponseDTO(Usuario usuario);

    List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios);
}