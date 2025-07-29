package com.numberia.OrcamentosAPI.Controllers;


import com.numberia.OrcamentosAPI.DTOs.AuthenticationDTO;
import com.numberia.OrcamentosAPI.DTOs.LoginResponseDTO;
import com.numberia.OrcamentosAPI.Infra.Security.TokenService;
import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orcamentos/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.senha());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.genToken((UsuarioModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
