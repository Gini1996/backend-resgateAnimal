package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhowsoftware.resgateanimal.repositories.DenunciaRepository;

@Service
public class DenunciaServiceImpl implements DenunciaService
{
	@Autowired
	private DenunciaRepository denunciaRepository;
	
	//TODO :Implementar metodos posteriormente
}
