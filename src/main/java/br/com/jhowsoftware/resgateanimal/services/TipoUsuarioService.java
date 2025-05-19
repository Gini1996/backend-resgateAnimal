package br.com.jhowsoftware.resgateanimal.services;

import br.com.jhowsoftware.resgateanimal.dtos.TipoUsuarioDTO;

import java.util.List;

public interface TipoUsuarioService
{
    List<TipoUsuarioDTO> findAll();
    TipoUsuarioDTO findById(Long id);
    void deletarTipoUsuario(Long id);
    TipoUsuarioDTO adicionarTipoUsuario(String tipoUsuario);
    TipoUsuarioDTO atualizarTipoUsuario(Long id, String tipoUsuario);
}