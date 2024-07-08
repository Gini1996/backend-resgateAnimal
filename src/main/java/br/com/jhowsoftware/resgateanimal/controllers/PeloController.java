package br.com.jhowsoftware.resgateanimal.controllers;

import br.com.jhowsoftware.resgateanimal.services.PeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pelo")
public class PeloController 
{
	@Autowired
	private PeloService peloService;
	
	//TODO :Implementar metodos posteriormente
}
