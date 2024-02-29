package br.com.jhowsoftware.resgateanimal.services;


import java.util.List;
import java.util.Optional;

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
		List<TipoDenuncia> resultTpDenuncia = tipoDenunciaRepository.findAll();
		List<TipoDenunciaDTO> dtoTpDenuncia = resultTpDenuncia.stream().map(x -> new TipoDenunciaDTO(x)).toList();
		
		return dtoTpDenuncia;
	}
	
	@Transactional(readOnly = true)
	public TipoDenunciaDTO findById(Long id)
	{
		TipoDenuncia result = tipoDenunciaRepository.findById(id).get();
		return new TipoDenunciaDTO(result);	
	}
	
	@Transactional
	public void deletarTipoDenuncia(Long id)
	{
		if(!tipoDenunciaRepository.existsById(id))
			throw new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados");
		
		tipoDenunciaRepository.deleteById(id);
	}
	
	@Transactional
	public void adicionarTipoDenuncia(String tpDenuncia)
	{ 
		validaString(tpDenuncia);
		
		if (tipoDenunciaRepository.existsByTipoDenuncia(tpDenuncia)) 
			throw new RegistroDuplicadoException("O tipo de denuncia: " + tpDenuncia + " já se encontra cadastrado no banco de dados");
			
		TipoDenuncia tipoDenuncia = new TipoDenuncia();
		tipoDenuncia.setTipoDenuncia(tpDenuncia);
			
		tipoDenunciaRepository.save(tipoDenuncia); 
	}
	
	@Transactional
	public void atualizarTipoDenuncia(Long id, String tpDenuncia)
	{
		if(!tipoDenunciaRepository.existsById(id))
			throw new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados");
		
		validaString(tpDenuncia);
		
		Optional<TipoDenuncia> optTipoDenuncia = tipoDenunciaRepository.findById(id);

        if(optTipoDenuncia.isPresent()) 
        {
            TipoDenuncia tipoDenuncia = optTipoDenuncia.get();
            tipoDenuncia.setTipoDenuncia(tpDenuncia);
            
            tipoDenunciaRepository.save(tipoDenuncia);
        }
	}
}