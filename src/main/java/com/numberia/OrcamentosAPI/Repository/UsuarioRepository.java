package com.numberia.OrcamentosAPI.Repository;

import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    UserDetails findByEmail(String email);
}
