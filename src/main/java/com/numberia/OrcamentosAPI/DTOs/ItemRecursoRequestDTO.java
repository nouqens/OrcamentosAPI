package com.numberia.OrcamentosAPI.DTOs;

import java.util.UUID;

public class ItemRecursoRequestDTO {

    private Double quantidade;

    private UUID obraId;

    private UUID recursoId;


    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public UUID getObraId() {
        return obraId;
    }

    public void setObraId(UUID obraId) {
        this.obraId = obraId;
    }

    public UUID getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(UUID recursoId) {
        this.recursoId = recursoId;
    }
}
