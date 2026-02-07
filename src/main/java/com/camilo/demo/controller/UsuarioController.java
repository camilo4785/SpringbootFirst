package com.camilo.demo.controller;

import com.camilo.demo.dto.UsuarioRequestDTO;
import com.camilo.demo.dto.UsuarioResponseDTO;
import com.camilo.demo.model.Usuario;
import com.camilo.demo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuarios() {
        return ResponseEntity.ok(service.obtenerTodos());
    }


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> guardar(
            @Valid @RequestBody UsuarioRequestDTO dto) {

        return ResponseEntity.ok(service.guardar(dto));
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarUsuario(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    @GetMapping("/buscarDireccion")
    public ResponseEntity<List<UsuarioResponseDTO>>  buscarDireccion(@RequestParam String direccion) {
        return ResponseEntity.ok(service.buscarPorDireccion(direccion));}

    @GetMapping("/genero")
    public ResponseEntity<List<UsuarioResponseDTO>>  buscarGenero(@RequestParam String genero) {
        return ResponseEntity.ok(service.buscarPorGenero(genero));}


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

}
