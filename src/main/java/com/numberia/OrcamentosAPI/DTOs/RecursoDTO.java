package com.numberia.OrcamentosAPI.DTOs;


import com.numberia.OrcamentosAPI.Models.RecursoModel;

import java.util.UUID;

public class RecursoDTO {
    private UUID id;
    private String descricao;
    private String unidade;
    private double precoUnitario;
    private String tipo;
    private UUID usuarioId;


    public RecursoDTO() {
    }

    public RecursoDTO(RecursoModel recursoModel) {
        this.id = recursoModel.getId();
        this.descricao = recursoModel.getDescricao();
        this.unidade = recursoModel.getUnidade();
        this.precoUnitario = recursoModel.getPrecoUnitario();
        this.tipo = recursoModel.getTipo();
        this.usuarioId = recursoModel.getUsuario().getId();

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }
}
