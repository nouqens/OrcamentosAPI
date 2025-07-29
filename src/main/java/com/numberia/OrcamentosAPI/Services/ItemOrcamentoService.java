package com.numberia.OrcamentosAPI.Services;

import com.numberia.OrcamentosAPI.DTOs.ItemOrcamentoDTO;
import com.numberia.OrcamentosAPI.DTOs.ItemRecursoRequestDTO;
import com.numberia.OrcamentosAPI.Models.ItemOrcamentoModel;
import com.numberia.OrcamentosAPI.Models.ObraModel;
import com.numberia.OrcamentosAPI.Models.RecursoModel;
import com.numberia.OrcamentosAPI.Repository.ItemOrcamentoRepository;
import com.numberia.OrcamentosAPI.Repository.ObraRepository;
import com.numberia.OrcamentosAPI.Repository.RecursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemOrcamentoService {
    private final ItemOrcamentoRepository itemOrcamentoRepository;
    private final ObraRepository obraRepository;
    private final RecursoRepository recursoRepository;

    public ItemOrcamentoService(ItemOrcamentoRepository itemOrcamentoRepository, ObraRepository obraRepository, RecursoRepository recursoRepository) {
        this.itemOrcamentoRepository = itemOrcamentoRepository;
        this.obraRepository = obraRepository;
        this.recursoRepository = recursoRepository;
    }

    public List<ItemOrcamentoDTO> getAll(){
        List<ItemOrcamentoModel> itemOrcamentoModel = itemOrcamentoRepository.findAll();
        return itemOrcamentoModel.stream().map(ItemOrcamentoDTO::new).toList();
    }

    public ItemOrcamentoDTO get(UUID id){
        ItemOrcamentoModel itemOrcamentoModel = itemOrcamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Orcamento não encontrado!"));
        return new ItemOrcamentoDTO(itemOrcamentoModel);
    }

    public ItemOrcamentoDTO save(ItemRecursoRequestDTO dto) {
        ObraModel obra = obraRepository.findById(dto.getObraId())
                .orElseThrow(() -> new RuntimeException("Obra não encontrada"));
        RecursoModel recurso = recursoRepository.findById(dto.getRecursoId())
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado"));

        double precoUnitario = recurso.getPrecoUnitario();
        double precoTotal = precoUnitario * dto.getQuantidade();

        ItemOrcamentoModel item = new ItemOrcamentoModel();
        item.setObra(obra);
        item.setRecurso(recurso);
        item.setQuantidade(dto.getQuantidade());
        item.setPrecoUnitarioMomento(precoUnitario);
        item.setPrecoTotalItem(precoTotal);

        return new ItemOrcamentoDTO(itemOrcamentoRepository.save(item));
    }

    public ItemOrcamentoModel delete(UUID id){
        ItemOrcamentoModel exist = itemOrcamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Orcamento não encontrado"));
        itemOrcamentoRepository.deleteById(id);
        return exist;
    }
}
