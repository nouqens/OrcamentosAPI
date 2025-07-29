package com.numberia.OrcamentosAPI.Models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_itens_orcamento")
public class ItemOrcamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "obra_id")
    private ObraModel obra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recurso_id")
    private RecursoModel recurso;

    private Double quantidade;


    @Column(name = "preco_unitario_momento")
    private Double precoUnitarioMomento;

    @Column(name = "preco_total_item")
    private Double precoTotalItem;

    public ItemOrcamentoModel(UUID id, ObraModel obra, RecursoModel recurso, Double quantidade, Double precoUnitarioMomento, Double precoTotalItem) {
        this.id = id;
        this.obra = obra;
        this.recurso = recurso;
        this.quantidade = quantidade;
        this.precoUnitarioMomento = precoUnitarioMomento;
        this.precoTotalItem = precoTotalItem;
    }

    public ItemOrcamentoModel() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ObraModel getObra() {
        return obra;
    }

    public void setObra(ObraModel obra) {
        this.obra = obra;
    }

    public RecursoModel getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoModel recurso) {
        this.recurso = recurso;
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
