package com.numberia.OrcamentosAPI.Repository;

import com.numberia.OrcamentosAPI.Models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {

    @Query(value = "SELECT c FROM tb_clientes c WHERE c.nome = :name", nativeQuery = true)
    List<ClienteModel> findByName(@Param("name") String name);


    @Query(value = "SELECT * FROM tb_clientes WHERE usuario_id = :usuario_id", nativeQuery = true)
    List<ClienteModel> findClienteByUsuarioId(@Param("usuario_id") UUID id);
}
