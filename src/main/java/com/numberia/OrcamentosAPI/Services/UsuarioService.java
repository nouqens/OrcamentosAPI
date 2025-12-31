package com.numberia.OrcamentosAPI.Services;

import com.numberia.OrcamentosAPI.DTOs.ClienteDTO;
import com.numberia.OrcamentosAPI.DTOs.UsuarioDTO;
import com.numberia.OrcamentosAPI.DTOs.UsuarioRequestDTO;
import com.numberia.OrcamentosAPI.Infra.Security.TokenService;
import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import com.numberia.OrcamentosAPI.Repository.ClienteRepository;
import com.numberia.OrcamentosAPI.Repository.UsuarioRepository;
import com.numberia.OrcamentosAPI.Roles.UsuarioRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private TokenService tokenService;

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;


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

    public UsuarioRequestDTO getMe(String token) {

        String data = tokenService.validateToken(token);
        Optional<UsuarioModel> user = usuarioRepository.findOptionalByEmail(data);
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome(user.get().getNome());
        dto.setId(user.get().getId());
        dto.setEmail(data);
        return dto;
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

    public List<ClienteDTO> getAllClientes(UUID id){
        return clienteRepository.findByUsuarioId(id)
                .stream()
                .map(c -> new ClienteDTO(
                        c.getNome(),
                        c.getTelefone(),
                        c.getEndereco(),
                        c.getObservacao(),
                        c.getId()
                ))
                .toList();
    }

}
