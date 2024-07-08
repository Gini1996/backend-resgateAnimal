package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhowsoftware.resgateanimal.repositories.TipoUsuarioRepository;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService
{
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	
	//TODO :Implementar metodos posteriormente
}