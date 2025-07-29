package com.numberia.OrcamentosAPI.Services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.numberia.OrcamentosAPI.DTOs.UsuarioDTO;
import com.numberia.OrcamentosAPI.DTOs.UsuarioRequestDTO;
import com.numberia.OrcamentosAPI.Models.ClienteModel;
import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import com.numberia.OrcamentosAPI.Repository.ClienteRepository;
import com.numberia.OrcamentosAPI.Repository.UsuarioRepository;
import com.numberia.OrcamentosAPI.Roles.UsuarioRole;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<UsuarioRequestDTO> getAll(){
        List<UsuarioModel> usuarioModel = usuarioRepository.findAll();
        return usuarioModel.stream().map(UsuarioRequestDTO::new).toList();
    }

    public List<UsuarioDTO> adminGetAll(){
        List<UsuarioModel> usuarioModel = usuarioRepository.findAll();
        return usuarioModel.stream().map(UsuarioDTO::new).toList();
    }

    public UsuarioDTO get(UUID id) {
        UsuarioModel usuarioModel = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return new UsuarioDTO(usuarioModel);
    }


    public UsuarioModel save(UsuarioModel usuarioModel){
        if(usuarioRepository.findByEmail(usuarioModel.getEmail())!=null){
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }

        String bCrypt = new BCryptPasswordEncoder().encode(usuarioModel.getPassword());
        usuarioModel.setRole(UsuarioRole.USER);
        usuarioModel.setSenha(bCrypt);
        return usuarioRepository.save(usuarioModel);
    }

    public UsuarioModel saveAdmin(UsuarioModel usuarioModel){
        if(usuarioRepository.findByEmail(usuarioModel.getEmail())!=null){
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }

        String bCrypt = new BCryptPasswordEncoder().encode(usuarioModel.getPassword());
        usuarioModel.setRole(UsuarioRole.ADMIN);
        usuarioModel.setSenha(bCrypt);
        return usuarioRepository.save(usuarioModel);
    }

    public UsuarioModel delete(UUID id) {

        UsuarioModel exist = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        usuarioRepository.deleteById(id);
        return exist;
    }

    public List<ClienteModel> getAllClientes(UUID id){
        return clienteRepository.findClienteByUsuarioId(id);
    }

}
