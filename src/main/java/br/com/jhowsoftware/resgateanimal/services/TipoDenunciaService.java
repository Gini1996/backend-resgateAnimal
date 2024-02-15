package br.com.jhowsoftware.resgateanimal.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;
import br.com.jhowsoftware.resgateanimal.exceptions.RegistroDuplicadoException;
import br.com.jhowsoftware.resgateanimal.repositories.TipoDenunciaRepository;

@Service
public class TipoDenunciaService 
{
	@Autowired
	private TipoDenunciaRepository tipoDenunciaRepository;
	
	@Transactional
	public void addTpDenuncia(String tpDenuncia)
	{ 
		if (tipoDenunciaRepository.existsByTipoDenuncia(tpDenuncia)) 
			throw new RegistroDuplicadoException("O tipo de denuncia: " + tpDenuncia + " já se encontra cadastrado no banco de dados");
			
		TipoDenuncia tipoDenuncia = new TipoDenuncia();
		tipoDenuncia.setTipoDenuncia(tpDenuncia);
			
		tipoDenunciaRepository.save(tipoDenuncia); 
	}
}