package com.numberia.OrcamentosAPI.Services;

import com.numberia.OrcamentosAPI.Models.ClienteModel;
import com.numberia.OrcamentosAPI.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public List<ClienteModel> getAll() {
        return clienteRepository.findAll();
    }

    public ClienteModel get(UUID id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("não encontrado"));
    }

    public ClienteModel save(ClienteModel clienteModel){
        return clienteRepository.save(clienteModel);
    }

    public ClienteModel update(ClienteModel clienteModel, UUID id) {
        ClienteModel exist = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("não encontrado"));
        exist.setEndereco(clienteModel.getEndereco());
        exist.setNome(clienteModel.getNome());
        exist.setObras(clienteModel.getObras());
        exist.setTelefone(clienteModel.getTelefone());
        exist.setObservacao(clienteModel.getObservacao());
        return clienteRepository.save(clienteModel);
    }

    public ClienteModel delete(UUID id) {
        ClienteModel exist = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("não encontrado"));
        clienteRepository.deleteById(id);
        return exist;
    }

    public List<ClienteModel> findByName(String name){
        return clienteRepository.findByName(name);
    }
}
