package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.ColoracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhowsoftware.resgateanimal.repositories.ColoracaoRepository;

@Service
public class ColoracaoServiceImpl implements ColoracaoService
{
	@Autowired
	private ColoracaoRepository coloracaoRepository;
	
	//TODO :Implementar metodos posteriormente
}