package br.com.jhowsoftware.resgateanimal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhowsoftware.resgateanimal.services.TipoDenunciaService;

@RestController
@RequestMapping(value = "/tipodenuncia")
public class TipoDenunciaController 
{
	@Autowired
	private TipoDenunciaService tipoDenunciaService;
	
	//TODO :Implementar metodos posteriormente
}