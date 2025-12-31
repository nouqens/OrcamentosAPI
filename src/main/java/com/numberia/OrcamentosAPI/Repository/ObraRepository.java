package com.numberia.OrcamentosAPI.Repository;

import com.numberia.OrcamentosAPI.DTOs.ObraDTO;
import com.numberia.OrcamentosAPI.Models.ObraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ObraRepository extends JpaRepository<ObraModel, UUID> {
    @Query("""
       SELECT new com.numberia.OrcamentosAPI.DTOs.ObraDTO(
           o.id,
           o.titulo,
           o.status,
           o.descricao,
           u.id,
           c.id
           
       )
       FROM ObraModel o
       JOIN o.usuario u
       LEFT JOIN o.cliente c
       WHERE u.id = :usuarioId
       """)
    List<ObraDTO> findByUsuarioId(UUID usuarioId);

}
