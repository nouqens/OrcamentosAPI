package com.numberia.OrcamentosAPI.Repository;

import com.numberia.OrcamentosAPI.Models.RecursoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecursoRepository extends JpaRepository<RecursoModel, UUID> {
}
