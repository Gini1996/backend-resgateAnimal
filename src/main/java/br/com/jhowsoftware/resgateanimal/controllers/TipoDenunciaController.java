package br.com.jhowsoftware.resgateanimal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.services.TipoDenunciaService;

@RestController
@RequestMapping(value = "/tipodenuncia")
public class TipoDenunciaController 
{
	@Autowired
	private TipoDenunciaService tipoDenunciaService;
	
	@PostMapping("/addTpDenuncia")
	public ResponseEntity<String> addTpDenuncia(@RequestBody TipoDenunciaDTO body)
	{
		try 
		{
			Integer tipoDenunciaInt = Integer.parseInt(body.getTipoDenuncia());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O valor " + tipoDenunciaInt + " é um numero, favor informar um texto valido");
		}
		catch(NumberFormatException e)
		{
			tipoDenunciaService.addTpDenuncia(body.getTipoDenuncia());
			return ResponseEntity.status(HttpStatus.OK).body("Tipo de denúncia cadastrado com sucesso.");   
		}
	}
}