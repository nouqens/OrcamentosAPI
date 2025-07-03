package com.numberia.OrcamentosAPI.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_itens_orcamento")
public class ItemOrcamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "obra_id")
    @JsonIgnore
    private ObraModel obra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recurso_id")
    private RecursoModel recurso;

    private double quantidade;


    @Column(name = "preco_unitario_momento")
    private double precoUnitarioMomento;

    @Column(name = "preco_total_item")
    private double precoTotalItem;



}
