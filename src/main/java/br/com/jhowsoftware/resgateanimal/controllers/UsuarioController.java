package br.com.jhowsoftware.resgateanimal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhowsoftware.resgateanimal.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController 
{
	@Autowired
	private UsuarioService usuarioService;
	
	//TODO :Implementar metodos posteriormente
}