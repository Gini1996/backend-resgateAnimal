package br.com.jhowsoftware.resgateanimal.services.impl;


import java.util.List;

import br.com.jhowsoftware.resgateanimal.services.TipoDenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroDuplicadoException;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroInexistente;
import br.com.jhowsoftware.resgateanimal.repositories.TipoDenunciaRepository;
import br.com.jhowsoftware.resgateanimal.utils.ServiceUtils;

@Service
public class TipoDenunciaServiceImpl implements TipoDenunciaService
{
	@Autowired
	private TipoDenunciaRepository tipoDenunciaRepository;

	@Autowired
	private ServiceUtils serviceUtils;

	@Transactional(readOnly = true)
	public List<TipoDenunciaDTO> findAll() throws RegistroInexistente
    {
	    try 
	    {
	        List<TipoDenuncia> resultTpDenuncia = tipoDenunciaRepository.findAll();
            return resultTpDenuncia.stream().map(TipoDenunciaDTO::new).toList();
	    } 
	    catch (Exception e) 
	    {
	        throw new RegistroInexistente("Não existem denuncias cadastradas");
	    }
	}
	
	@Transactional(readOnly = true)
	public TipoDenunciaDTO findById(Long id)
	{
		TipoDenuncia result = tipoDenunciaRepository.findById(id)
							  .orElseThrow(() -> new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados"));
	    return new TipoDenunciaDTO(result);
	}
	
	@Transactional
	public void deletarTipoDenuncia(Long id)
	{
		final String idTabela = "id_tipo_denuncia";
		final String tabela = "tipo_denuncia";
		
        if(!tipoDenunciaRepository.existsById(id))
        	throw new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados");
        
        tipoDenunciaRepository.deleteById(id);
        serviceUtils.reiniciarSequencia(idTabela,tabela);
	}
	
	@Transactional
	public TipoDenunciaDTO adicionarTipoDenuncia(String tpDenuncia) 
	{
		serviceUtils.validaString(tpDenuncia);

        if (tipoDenunciaRepository.existsByTipoDenuncia(tpDenuncia)) 
            throw new RegistroDuplicadoException("O tipo de denuncia: " + tpDenuncia + " já se encontra cadastrado no banco de dados");

        TipoDenuncia tipoDenuncia = new TipoDenuncia();
        tipoDenuncia.setTipoDenuncia(tpDenuncia);

        TipoDenuncia save = tipoDenunciaRepository.save(tipoDenuncia);
        
        TipoDenunciaDTO tipoDenunciaDTO = new TipoDenunciaDTO();
        tipoDenunciaDTO.setIdTipoDenuncia(save.getIdTipoDenuncia());
        tipoDenunciaDTO.setTipoDenuncia(save.getTipoDenuncia());

        return tipoDenunciaDTO;
	}
	
	@Transactional
	public TipoDenunciaDTO atualizarTipoDenuncia(Long id, String tpDenuncia)
	{
		serviceUtils.validaString(tpDenuncia);
		
		TipoDenuncia tipoDenuncia = tipoDenunciaRepository.findById(id)
									.orElseThrow(() -> new RegistroInexistente("Tipo de denúncia não encontrado com o ID: " + id));
        
		tipoDenuncia.setTipoDenuncia(tpDenuncia);
         
		TipoDenuncia save = tipoDenunciaRepository.save(tipoDenuncia);
		
		TipoDenunciaDTO tipoDenunciaDTO = new TipoDenunciaDTO();
        tipoDenunciaDTO.setIdTipoDenuncia(save.getIdTipoDenuncia());
        tipoDenunciaDTO.setTipoDenuncia(save.getTipoDenuncia());

        return tipoDenunciaDTO;
	}
}