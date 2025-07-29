package com.numberia.OrcamentosAPI.Controllers;

import com.numberia.OrcamentosAPI.DTOs.ClienteDTO;
import com.numberia.OrcamentosAPI.Models.ClienteModel;
import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import com.numberia.OrcamentosAPI.Services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orcamentos/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Buscar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable UUID id) {
        ClienteDTO clienteDTO = clienteService.get(id);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    // Buscar cliente por nome (via query param)
    @GetMapping("/buscar-por-nome")
    public List<ClienteModel> getByName(@RequestParam String name) {
        return clienteService.findByName(name);
    }

    // Criar novo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> save(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO savedCliente = clienteService.save(clienteDTO);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    // Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> update(@RequestBody ClienteModel clienteModel, @PathVariable UUID id) {
        ClienteModel updatedCliente = clienteService.update(clienteModel, id);
        return ResponseEntity.ok(updatedCliente);
    }

    // Deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteModel> delete(@PathVariable UUID id) {
        ClienteModel delete = clienteService.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioModel> findUsuarioByCliente(@PathVariable UUID id){
        UsuarioModel usuarioModel = clienteService.findUsuarioByCliente(id);
        return new ResponseEntity<>(usuarioModel, HttpStatus.OK);
    }
}
