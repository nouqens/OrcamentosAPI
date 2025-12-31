package com.numberia.OrcamentosAPI.Repository;

import com.numberia.OrcamentosAPI.DTOs.RecursoDTO;
import com.numberia.OrcamentosAPI.Models.RecursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RecursoRepository extends JpaRepository<RecursoModel, UUID> {
    @Query("""
    SELECT new com.numberia.OrcamentosAPI.DTOs.RecursoDTO(
        r.id,
        r.descricao,
        r.unidade,
        r.precoUnitario,
        r.tipo,
        u.id
    )
    FROM RecursoModel r
    JOIN r.usuario u
    WHERE u.id = :usuarioId
    """)
    List<RecursoDTO> findAllByUser(@Param("usuarioId") UUID usuarioId);
}
