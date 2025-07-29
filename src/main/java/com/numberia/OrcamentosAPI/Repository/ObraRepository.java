package com.numberia.OrcamentosAPI.Repository;

import com.numberia.OrcamentosAPI.Models.ObraModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ObraRepository extends JpaRepository<ObraModel, UUID> {
}
