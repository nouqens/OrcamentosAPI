package com.numberia.OrcamentosAPI.Services;

import com.numberia.OrcamentosAPI.DTOs.ClienteDTO;
import com.numberia.OrcamentosAPI.Models.ClienteModel;
import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import com.numberia.OrcamentosAPI.Repository.ClienteRepository;
import com.numberia.OrcamentosAPI.Repository.UsuarioRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public ClienteService(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ClienteDTO> getAll() {
        List<ClienteModel> clienteModel = clienteRepository.findAll();
        return clienteModel.stream().map(ClienteDTO::new).toList();
    }

    public ClienteDTO get(UUID id) {
        ClienteModel clienteModel = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        return new ClienteDTO(clienteModel);
    }

    public ClienteDTO save(ClienteDTO clienteDTO){

        UsuarioModel usuario = usuarioRepository.findById(clienteDTO.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome(clienteDTO.getNome());
        clienteModel.setObservacao(clienteDTO.getObservacao());
        clienteModel.setTelefone(clienteDTO.getTelefone());
        clienteModel.setEndereco(clienteDTO.getEndereco());
        clienteModel.setObras(clienteModel.getObras());
        clienteModel.setUsuario(usuario);
        clienteModel.setId(clienteDTO.getId());

        return new ClienteDTO(clienteRepository.save(clienteModel));
    }

    public ClienteModel update(@NotNull ClienteModel clienteModel, UUID id) {
        ClienteModel exist = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        exist.setEndereco(clienteModel.getEndereco());
        exist.setNome(clienteModel.getNome());
        exist.setObras(clienteModel.getObras());
        exist.setTelefone(clienteModel.getTelefone());
        exist.setObservacao(clienteModel.getObservacao());
        return clienteRepository.save(exist);
    }

    public ClienteModel delete(UUID id) {
        ClienteModel exist = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        clienteRepository.deleteById(id);
        return exist;
    }

    public ClienteDTO findByNome(String nome){
        ClienteModel clienteModel = clienteRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        return new ClienteDTO(clienteModel);
    }

    public UsuarioModel findUsuarioByCliente(UUID id){
        ClienteModel exist = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        UUID userId = exist.getUsuario().getId();
        return usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

    }
}
