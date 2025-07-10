package com.numberia.OrcamentosAPI.Controllers;

import com.numberia.OrcamentosAPI.Models.ClienteModel;
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
    public List<ClienteModel> getAll() {
        return clienteService.getAll();
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> getById(@PathVariable UUID id) {
        ClienteModel cliente = clienteService.get(id);
        return ResponseEntity.ok(cliente);
    }

    // Buscar cliente por nome (via query param)
    @GetMapping("/buscar-por-nome")
    public List<ClienteModel> getByName(@RequestParam String name) {
        return clienteService.findByName(name);
    }

    // Criar novo cliente
    @PostMapping
    public ResponseEntity<ClienteModel> save(@RequestBody ClienteModel clienteModel) {
        ClienteModel savedCliente = clienteService.save(clienteModel);
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
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
