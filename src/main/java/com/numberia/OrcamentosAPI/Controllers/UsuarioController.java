package com.numberia.OrcamentosAPI.Controllers;


import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UsuarioController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "oi";
    }

    @PostMapping("/cadastrarusuario")
    public String cadastrarUsuario(String nome, String email, byte idade){
        UsuarioModel u = new UsuarioModel(nome, email, idade);

        return "Cadastrado";

    }

}
