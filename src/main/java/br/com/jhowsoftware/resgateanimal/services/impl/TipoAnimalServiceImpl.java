package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.TipoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhowsoftware.resgateanimal.repositories.TipoAnimalRepository;

@Service
public class TipoAnimalServiceImpl implements TipoAnimalService
{
	@Autowired
	private TipoAnimalRepository tipoAnimalRepository;
	
	//TODO :Implementar metodos posteriormente
}