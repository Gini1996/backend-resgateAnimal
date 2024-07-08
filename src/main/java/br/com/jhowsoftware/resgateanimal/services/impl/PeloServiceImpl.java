package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.PeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhowsoftware.resgateanimal.repositories.PeloRepository;

@Service
public class PeloServiceImpl implements PeloService
{
	@Autowired
	private PeloRepository peloRepository;
	
	//TODO :Implementar metodos posteriormente
}
