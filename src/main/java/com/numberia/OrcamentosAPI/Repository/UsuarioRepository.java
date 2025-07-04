package com.numberia.OrcamentosAPI.Repository;

import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
}
