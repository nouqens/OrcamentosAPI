package com.numberia.OrcamentosAPI.DTOs;

import com.numberia.OrcamentosAPI.Models.ItemOrcamentoModel;

import java.util.UUID;

public class ItemOrcamentoDTO {
    private UUID id;
    private UUID obraId;
    private UUID recursoId;
    private Double quantidade;
    private Double precoUnitarioMomento;
    private Double precoTotalItem;

    public ItemOrcamentoDTO(ItemOrcamentoModel itemOrcamentoModel) {
        this.id = itemOrcamentoModel.getId();
        this.obraId = itemOrcamentoModel.getObra().getId();
        this.quantidade = itemOrcamentoModel.getQuantidade();
        this.precoUnitarioMomento = itemOrcamentoModel.getPrecoUnitarioMomento();
        this.precoTotalItem = itemOrcamentoModel.getPrecoTotalItem();
        this.recursoId = itemOrcamentoModel.getRecurso().getId();
    }

    public UUID getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(UUID recursoId) {
        this.recursoId = recursoId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getObraId() {
        return obraId;
    }

    public void setObraId(UUID obraId) {
        this.obraId = obraId;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitarioMomento() {
        return precoUnitarioMomento;
    }

    public void setPrecoUnitarioMomento(double precoUnitarioMomento) {
        this.precoUnitarioMomento = precoUnitarioMomento;
    }

    public double getPrecoTotalItem() {
        return precoTotalItem;
    }

    public void setPrecoTotalItem(double precoTotalItem) {
        this.precoTotalItem = precoTotalItem;
    }
}
