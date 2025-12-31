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

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable UUID id) {
        ClienteDTO clienteDTO = clienteService.get(id);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }


    @GetMapping("/name/{nome}")
    public ResponseEntity<ClienteDTO> getByNome(@PathVariable String nome) {
        return new ResponseEntity<>(clienteService.findByNome(nome), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO) {
        try{
            ClienteDTO saved = clienteService.save(clienteDTO);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> update(@RequestBody ClienteModel clienteModel, @PathVariable UUID id) {
        ClienteModel updatedCliente = clienteService.update(clienteModel, id);
        return ResponseEntity.ok(updatedCliente);
    }

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
