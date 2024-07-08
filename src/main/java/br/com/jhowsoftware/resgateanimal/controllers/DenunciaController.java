package br.com.jhowsoftware.resgateanimal.controllers;

import br.com.jhowsoftware.resgateanimal.services.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/denuncia")
public class DenunciaController 
{
	@Autowired
	private DenunciaService denunciaService;
	
	//TODO :Implementar metodos posteriormente
}