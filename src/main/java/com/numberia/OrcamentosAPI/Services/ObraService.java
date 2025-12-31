package com.numberia.OrcamentosAPI.Services;

import com.numberia.OrcamentosAPI.DTOs.ItemOrcamentoDTO;
import com.numberia.OrcamentosAPI.DTOs.ObraDTO;
import com.numberia.OrcamentosAPI.Models.ClienteModel;
import com.numberia.OrcamentosAPI.Models.ItemOrcamentoModel;
import com.numberia.OrcamentosAPI.Models.ObraModel;
import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import com.numberia.OrcamentosAPI.Repository.ClienteRepository;
import com.numberia.OrcamentosAPI.Repository.ItemOrcamentoRepository;
import com.numberia.OrcamentosAPI.Repository.ObraRepository;
import com.numberia.OrcamentosAPI.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ObraService {
    private final ObraRepository obraRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ItemOrcamentoRepository itemOrcamentoRepository;


    public ObraService(ObraRepository obraRepository, ClienteRepository clienteRepository, UsuarioRepository usuarioRepository, ItemOrcamentoRepository itemOrcamentoRepository) {
        this.obraRepository = obraRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.itemOrcamentoRepository = itemOrcamentoRepository;
    }

    public List<ObraDTO> getAll(){
        List<ObraModel> obraModels = obraRepository.findAll();
        return obraModels.stream().map(ObraDTO::new).toList();
    }

    public ObraDTO get(UUID id){
        ObraModel obraModel = obraRepository.findById(id).orElseThrow(() -> new RuntimeException("Obra não encontrada!"));
        return new ObraDTO(obraModel);
    }

    public List<ObraDTO> getByUser(UUID id){
         return obraRepository.findByUsuarioId(id);
    }

    public ObraDTO save(ObraDTO dto) {
        ClienteModel cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        UsuarioModel usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ObraModel obra = new ObraModel();
        obra.setTitulo(dto.getTitulo());
        obra.setDescricao(dto.getDescricao());
        obra.setStatus(dto.getStatus());
        obra.setCliente(cliente);
        obra.setUsuario(usuario);

        return new ObraDTO(obraRepository.save(obra));
    }

    public ObraModel delete(UUID id){
        ObraModel exist = obraRepository.findById(id).orElseThrow(() -> new RuntimeException("Obra não encontrada"));
        obraRepository.deleteById(id);
        return exist;
    }
    
    public ObraDTO updateItensOrcamento(ObraDTO obraDTO, UUID id) {
        ObraModel exist = obraRepository.findById(id).orElseThrow(() -> new RuntimeException("Obra não encontrada!"));

        List<UUID> itemIds = obraDTO.getItensOrcamentoId();

        if (itemIds != null && !itemIds.contains(null)) {
            exist.setItensOrcamento(itemOrcamentoRepository.findAllById(itemIds));
        } else {
            exist.setItensOrcamento(new ArrayList<>());
        }


        return save(new ObraDTO(exist));
    }


    public List<ItemOrcamentoDTO> getTotalPrice(UUID id) {
        if (!obraRepository.existsById(id)) {
            throw new RuntimeException("Obra não encontrada!");
        }
        List<ItemOrcamentoModel> items = itemOrcamentoRepository.findByObra_Id(id);
        return items.stream().map(ItemOrcamentoDTO::new).toList();
    }
}
