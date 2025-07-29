package com.numberia.OrcamentosAPI.DTOs;

import com.numberia.OrcamentosAPI.Models.UsuarioModel;

import java.util.List;
import java.util.UUID;

public class UsuarioRequestDTO {
    private UUID id;
    private String nome;
    private String email;
    private List<RecursoDTO> recursos;
    private List<ClienteDTO> clientes;
    private List<ObraDTO> obras;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(UsuarioModel usuarioModel) {
        this.id = usuarioModel.getId();
        this.nome = usuarioModel.getNome();
        this.email = usuarioModel.getEmail();
        this.recursos = usuarioModel.getRecursos().stream().map(RecursoDTO::new).toList();
        this.clientes = usuarioModel.getClientes().stream().map(ClienteDTO::new).toList();
        this.obras = usuarioModel.getObras().stream().map(ObraDTO::new).toList();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RecursoDTO> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoDTO> recursos) {
        this.recursos = recursos;
    }

    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteDTO> clientes) {
        this.clientes = clientes;
    }

    public List<ObraDTO> getObras() {
        return obras;
    }

    public void setObras(List<ObraDTO> obras) {
        this.obras = obras;
    }
}
