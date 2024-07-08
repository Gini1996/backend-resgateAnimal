package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.PorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhowsoftware.resgateanimal.repositories.PorteRepository;

@Service
public class PorteServiceImpl implements PorteService
{
	@Autowired
	private PorteRepository porteRepository;
	
	//TODO :Implementar metodos posteriormente
}
