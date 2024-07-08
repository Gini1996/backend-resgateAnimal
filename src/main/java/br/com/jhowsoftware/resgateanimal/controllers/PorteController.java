package br.com.jhowsoftware.resgateanimal.controllers;

import br.com.jhowsoftware.resgateanimal.services.PorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/porte")
public class PorteController 
{
	@Autowired
	private PorteService porteService;
	
	//TODO :Implementar metodos posteriormente
}
