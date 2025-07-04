package com.numberia.OrcamentosAPI.Controllers;


import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import com.numberia.OrcamentosAPI.Services.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/boasvindas")
    public List<UsuarioModel> getAll() {
        return usuarioService.getAll();
    }

    @PostMapping("/cadastrarusuario")
    public UsuarioModel cadastrarUsuario(@RequestBody UsuarioModel usuarioModel){
        return usuarioService.save(usuarioModel);
    }

}
