package com.numberia.OrcamentosAPI.Services;

import com.numberia.OrcamentosAPI.Models.UsuarioModel;
import com.numberia.OrcamentosAPI.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> getAll(){
        return usuarioRepository.findAll();
    }

    public UsuarioModel save(UsuarioModel usuarioModel){
        return usuarioRepository.save(usuarioModel);
    }
    public void delete(UUID id){
        usuarioRepository.deleteById(id);


    }


}
