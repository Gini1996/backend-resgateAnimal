package br.com.jhowsoftware.resgateanimal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhowsoftware.resgateanimal.services.ColoracaoService;

@RestController
@RequestMapping(value = "/coloracao")
public class ColoracaoController 
{
	@Autowired
	private ColoracaoService coloracaoService;
	
	//TODO :Implementar metodos posteriormente
}