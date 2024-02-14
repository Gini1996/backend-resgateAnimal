package br.com.jhowsoftware.resgateanimal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhowsoftware.resgateanimal.services.ResgateService;

@RestController
@RequestMapping(value = "/resgate")
public class ResgateController 
{
	@Autowired
	private ResgateService resgateService;
	
	//TODO :Implementar metodos posteriormente
}