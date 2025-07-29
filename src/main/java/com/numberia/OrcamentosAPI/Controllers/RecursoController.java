package com.numberia.OrcamentosAPI.Controllers;

import com.numberia.OrcamentosAPI.DTOs.RecursoDTO;
import com.numberia.OrcamentosAPI.Models.RecursoModel;
import com.numberia.OrcamentosAPI.Services.RecursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orcamentos/recursos")
public class RecursoController {
    private final RecursoService recursoService;

    public RecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @GetMapping
    public ResponseEntity<List<RecursoDTO>> getAll(){
        return new ResponseEntity<>(recursoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> get(@PathVariable UUID id){
        RecursoDTO recursoDTO = recursoService.get(id);
        return new ResponseEntity<>(recursoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecursoDTO> save(@RequestBody RecursoDTO recursoDTO){
        RecursoDTO savedRecuso = recursoService.save(recursoDTO);
        return new ResponseEntity<>(savedRecuso, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecursoModel> delete(@PathVariable UUID id){
        RecursoModel deletedRecurso = recursoService.delete(id);
        return new ResponseEntity<>(deletedRecurso, HttpStatus.OK);
    }
}
