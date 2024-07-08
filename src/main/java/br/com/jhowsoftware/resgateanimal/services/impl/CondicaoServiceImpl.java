package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.CondicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhowsoftware.resgateanimal.repositories.CondicaoRepository;

@Service
public class CondicaoServiceImpl implements CondicaoService
{
	@Autowired
	private CondicaoRepository condicaoRepository;
	
	//TODO :Implementar metodos posteriormente
}
