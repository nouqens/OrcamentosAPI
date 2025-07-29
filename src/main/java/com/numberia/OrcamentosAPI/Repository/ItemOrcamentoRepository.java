package com.numberia.OrcamentosAPI.Repository;

import com.numberia.OrcamentosAPI.Models.ItemOrcamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemOrcamentoRepository extends JpaRepository<ItemOrcamentoModel, UUID> {
}
