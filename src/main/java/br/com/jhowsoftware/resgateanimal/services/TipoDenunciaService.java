package br.com.jhowsoftware.resgateanimal.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;
import br.com.jhowsoftware.resgateanimal.exceptions.RegistroDuplicadoException;
import br.com.jhowsoftware.resgateanimal.exceptions.RegistroInexistente;
import br.com.jhowsoftware.resgateanimal.repositories.TipoDenunciaRepository;
import br.com.jhowsoftware.resgateanimal.utils.ServiceUtils;

@Service
public class TipoDenunciaService extends ServiceUtils
{
	@Autowired
	private TipoDenunciaRepository tipoDenunciaRepository;
	
	@Transactional(readOnly = true)
	public List<TipoDenunciaDTO> findAll() 
	{
	    try 
	    {
	        List<TipoDenuncia> resultTpDenuncia = tipoDenunciaRepository.findAll();
	        List<TipoDenunciaDTO> dtoTpDenuncia = resultTpDenuncia.stream().map(x -> new TipoDenunciaDTO(x)).toList();
	        return dtoTpDenuncia;
	    } 
	    catch (Exception e) 
	    {
	        throw new RuntimeException("Erro ao buscar todos os tipos de denúncia", e);
	    }
	}
	
	@Transactional(readOnly = true)
	public TipoDenunciaDTO findById(Long id)
	{
		TipoDenuncia result = tipoDenunciaRepository.findById(id)
							  .orElseThrow(() -> new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados"));;
	    return new TipoDenunciaDTO(result);
	}
	
	@Transactional
	public void deletarTipoDenuncia(Long id)
	{
		final String idTabela = "id_tipo_denuncia";
		final String tabela = "tipo_denuncia";
		final String sequence = "public.tipo_denuncia_id_tipo_denuncia_seq";
		
        if(!tipoDenunciaRepository.existsById(id))
        	throw new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados");
        
        tipoDenunciaRepository.deleteById(id);
        reiniciarSequencia(idTabela,tabela,sequence);
	}
	
	@Transactional
	public TipoDenunciaDTO adicionarTipoDenuncia(String tpDenuncia) 
	{
		validaString(tpDenuncia);

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
		validaString(tpDenuncia);
		
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