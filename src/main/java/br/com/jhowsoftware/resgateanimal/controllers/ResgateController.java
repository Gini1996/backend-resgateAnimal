package br.com.jhowsoftware.resgateanimal.controllers;

import br.com.jhowsoftware.resgateanimal.services.ResgateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resgate")
public class ResgateController 
{
	@Autowired
	private ResgateService resgateService;
	
	//TODO :Implementar metodos posteriormente
}