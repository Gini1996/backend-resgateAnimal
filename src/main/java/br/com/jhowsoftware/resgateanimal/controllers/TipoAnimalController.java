package br.com.jhowsoftware.resgateanimal.controllers;

import br.com.jhowsoftware.resgateanimal.services.TipoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tipoanimal")
public class TipoAnimalController 
{
	@Autowired
	private TipoAnimalService tipoAnimalService;
	
	//TODO :Implementar metodos posteriormente
}