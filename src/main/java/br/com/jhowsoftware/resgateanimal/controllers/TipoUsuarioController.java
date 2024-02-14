package br.com.jhowsoftware.resgateanimal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhowsoftware.resgateanimal.services.TipoUsuarioService;

@RestController
@RequestMapping(value = "/tipousuario")
public class TipoUsuarioController 
{
	@Autowired
	private TipoUsuarioService tipoUsuarioService;
	
	//TODO :Implementar metodos posteriormente
}