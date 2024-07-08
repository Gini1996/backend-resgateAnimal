package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.ResgateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhowsoftware.resgateanimal.repositories.ResgateRepository;

@Service
public class ResgateServiceImpl implements ResgateService
{
	@Autowired
	private ResgateRepository regasteRepository;
	
	//TODO :Implementar metodos posteriormente
}
