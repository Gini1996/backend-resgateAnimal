package br.com.jhowsoftware.resgateanimal.services;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;

import java.util.List;

public interface TipoDenunciaService
{
    List<TipoDenunciaDTO> findAll();
    TipoDenunciaDTO findById(Long id);
    void deletarTipoDenuncia(Long id);
    TipoDenunciaDTO adicionarTipoDenuncia(String tpDenuncia);
    TipoDenunciaDTO atualizarTipoDenuncia(Long id, String tpDenuncia);
}
