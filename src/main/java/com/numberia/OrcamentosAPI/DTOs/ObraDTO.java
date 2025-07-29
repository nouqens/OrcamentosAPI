package com.numberia.OrcamentosAPI.DTOs;

import com.numberia.OrcamentosAPI.Models.ItemOrcamentoModel;
import com.numberia.OrcamentosAPI.Models.ObraModel;

import java.util.List;
import java.util.UUID;

public class ObraDTO {
    private UUID id;
    private String titulo;
    private String descricao;
    private String status;
    private UUID clienteId;
    private UUID usuarioId;
    private List<UUID> itensOrcamentoId;

    public ObraDTO() {

    }

    public ObraDTO(ObraModel obra) {
        this.id = obra.getId();
        this.titulo = obra.getTitulo();
        this.descricao = obra.getDescricao();
        this.status = obra.getStatus();
        this.clienteId = obra.getCliente().getId();
        this.usuarioId = obra.getUsuario().getId();

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<UUID> getItensOrcamentoId() {
        return itensOrcamentoId;
    }

    public void setItensOrcamentoId(List<UUID> itensOrcamentoId) {
        this.itensOrcamentoId = itensOrcamentoId;
    }
}
