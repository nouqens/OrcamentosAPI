package com.numberia.OrcamentosAPI.Controllers;

import com.numberia.OrcamentosAPI.DTOs.ObraDTO;
import com.numberia.OrcamentosAPI.Models.ObraModel;
import com.numberia.OrcamentosAPI.Services.ObraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orcamentos/obras")
public class ObraController {
    private final ObraService obraService;

    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    @GetMapping
    public ResponseEntity<List<ObraDTO>> getAll(){
        return new ResponseEntity<>(obraService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraDTO> get(@PathVariable UUID id){
        ObraDTO obraDTO = obraService.get(id);
        return new ResponseEntity<>(obraDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ObraDTO> save(@RequestBody ObraDTO obraDTO){
        ObraDTO savedObra = obraService.save(obraDTO);
        return new ResponseEntity<>(savedObra, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObraModel> delete(@PathVariable UUID id){
        ObraModel deleteObra = obraService.delete(id);
        return new ResponseEntity<>(deleteObra, HttpStatus.OK);
    }



}
