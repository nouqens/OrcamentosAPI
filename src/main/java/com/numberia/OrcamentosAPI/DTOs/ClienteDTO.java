package com.numberia.OrcamentosAPI.DTOs;

import com.numberia.OrcamentosAPI.Models.ClienteModel;
import com.numberia.OrcamentosAPI.Models.ObraModel;

import java.util.List;
import java.util.UUID;

public class ClienteDTO {
    private UUID id;
    private String nome;
    private String telefone;
    private String endereco;
    private String observacao;
    private List<UUID> obrasId;
    private UUID usuarioId;

    public ClienteDTO() {
    }

    public ClienteDTO(ClienteModel clienteModel) {
        this.id = clienteModel.getId();
        this.nome = clienteModel.getNome();
        this.telefone = clienteModel.getTelefone();
        this.endereco = clienteModel.getEndereco();
        this.observacao = clienteModel.getObservacao();
        if (clienteModel.getObras() != null){
            this.obrasId = clienteModel.getObras().stream().map(ObraModel::getId).toList();
        }
        this.usuarioId = clienteModel.getUsuario().getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<UUID> getObrasId() {
        return obrasId;
    }

    public void setObrasId(List<UUID> obrasId) {
        this.obrasId = obrasId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }
}
