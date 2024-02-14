package br.com.jhowsoftware.resgateanimal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhowsoftware.resgateanimal.services.CondicaoService;

@RestController
@RequestMapping(value = "/condicao")
public class CondicaoController 
{
	@Autowired
	private CondicaoService condicaoService;
	
	//TODO :Implementar metodos posteriormente
}
