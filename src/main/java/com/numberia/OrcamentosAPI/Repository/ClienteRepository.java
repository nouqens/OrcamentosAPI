package com.numberia.OrcamentosAPI.Repository;


import com.numberia.OrcamentosAPI.Models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {

    @Query("SELECT c FROM ClienteModel c WHERE c.nome = :nome")
    Optional<ClienteModel> findByNome(@Param("nome") String nome);

    @Query("SELECT c FROM ClienteModel c WHERE c.usuario.id = :usuarioId")
    List<ClienteModel> findByUsuarioId(@Param("usuarioId") UUID usuarioId);


}
