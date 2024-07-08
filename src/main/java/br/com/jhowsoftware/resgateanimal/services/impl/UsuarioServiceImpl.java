package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhowsoftware.resgateanimal.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService
{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//TODO :Implementar metodos posteriormente
}