package com.numberia.OrcamentosAPI.Controllers;


import com.numberia.OrcamentosAPI.DTOs.UsuarioDTO;
import com.numberia.OrcamentosAPI.DTOs.UsuarioRequestDTO;
import com.numberia.OrcamentosAPI.Models.ClienteModel;
import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import com.numberia.OrcamentosAPI.Services.RecursoService;
import com.numberia.OrcamentosAPI.Services.UsuarioService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orcamentos/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<List<UsuarioDTO>> adminGetAll() {
        return new ResponseEntity<>(usuarioService.adminGetAll(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioRequestDTO>> getAll() {
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> get(@PathVariable UUID id){
        UsuarioDTO usuarioDTO = usuarioService.get(id);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UsuarioModel usuarioModel){
        try {
            UsuarioModel saveUsuario = usuarioService.save(usuarioModel);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@RequestBody UsuarioModel usuarioModel){
        try {
            UsuarioModel saveUsuario = usuarioService.saveAdmin(usuarioModel);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioModel> delete(@PathVariable UUID id){
        UsuarioModel deleteUsuario = usuarioService.delete(id);
        return new ResponseEntity<>(deleteUsuario, HttpStatus.OK);
    }

    @GetMapping("/clientes/{id}")
    public List<ClienteModel> getAllClientes(@PathVariable UUID id){
        return usuarioService.getAllClientes(id);
    }

}
