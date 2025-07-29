package com.numberia.OrcamentosAPI.Services;

import com.numberia.OrcamentosAPI.DTOs.ObraDTO;
import com.numberia.OrcamentosAPI.DTOs.RecursoDTO;
import com.numberia.OrcamentosAPI.Models.RecursoModel;
import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import com.numberia.OrcamentosAPI.Repository.RecursoRepository;
import com.numberia.OrcamentosAPI.Repository.UsuarioRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecursoService {
    private final RecursoRepository recursoRepository;
    private final UsuarioRepository usuarioRepository;

    public RecursoService(RecursoRepository recursoRepository, UsuarioRepository usuarioRepository) {
        this.recursoRepository = recursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<RecursoDTO> getAll(){
        List<RecursoModel> recursoModels = recursoRepository.findAll();
        return recursoModels.stream().map(RecursoDTO::new).toList();
    }

    public RecursoDTO get(UUID id){
        RecursoModel recursoModel = recursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
        return new RecursoDTO(recursoModel);
    }

    public RecursoDTO save(@NotNull RecursoDTO recursoDTO){
        UsuarioModel usuarioModel = usuarioRepository.findById(recursoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        RecursoModel recurso = new RecursoModel();
        recurso.setId(recursoDTO.getId());
        recurso.setDescricao(recursoDTO.getDescricao());
        recurso.setUnidade(recursoDTO.getUnidade());
        recurso.setPrecoUnitario(recursoDTO.getPrecoUnitario());
        recurso.setTipo(recursoDTO.getTipo());
        recurso.setUsuario(usuarioModel);

        return new RecursoDTO(recursoRepository.save(recurso));
    }

    public RecursoModel delete(UUID id){
        RecursoModel exist =  recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado"));

        recursoRepository.deleteById(id);
        return exist;
    }

}
