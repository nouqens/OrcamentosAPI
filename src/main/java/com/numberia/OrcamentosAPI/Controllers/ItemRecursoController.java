package com.numberia.OrcamentosAPI.Controllers;


import com.numberia.OrcamentosAPI.DTOs.ItemOrcamentoDTO;
import com.numberia.OrcamentosAPI.DTOs.ItemRecursoRequestDTO;
import com.numberia.OrcamentosAPI.Models.ItemOrcamentoModel;
import com.numberia.OrcamentosAPI.Services.ItemOrcamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/orcamentos/item-recurso")
public class ItemRecursoController {
    private final ItemOrcamentoService itemOrcamentoService;

    public ItemRecursoController(ItemOrcamentoService itemOrcamentoService) {
        this.itemOrcamentoService = itemOrcamentoService;
    }

    @GetMapping
    public ResponseEntity<List<ItemOrcamentoDTO>> getAll(){
        return new ResponseEntity<>(itemOrcamentoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemOrcamentoDTO> get(@PathVariable UUID id){
        ItemOrcamentoDTO itemOrcamentoDTO = itemOrcamentoService.get(id);
        return new ResponseEntity<>(itemOrcamentoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemOrcamentoDTO> save(@RequestBody ItemRecursoRequestDTO dto) {
        ItemOrcamentoDTO savedItem = itemOrcamentoService.save(dto);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemOrcamentoModel> delete(@PathVariable UUID id){
        ItemOrcamentoModel deletedItemOrcamento = itemOrcamentoService.delete(id);
        return new ResponseEntity<>(deletedItemOrcamento, HttpStatus.OK);
    }
}
