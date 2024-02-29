package br.com.jhowsoftware.resgateanimal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public ResponseEntity<List<TipoDenunciaDTO>> findAll()
	{
		List<TipoDenunciaDTO> denuncias =  tipoDenunciaService.findAll();
		return denuncias != null ? ResponseEntity.ok(denuncias): ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<TipoDenunciaDTO> findById(@PathVariable Long id) 
	{
        TipoDenunciaDTO result = tipoDenunciaService.findById(id);
        return result != null ? ResponseEntity.ok(result): ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
	
	@PostMapping("/addTpDenuncia")
	public ResponseEntity<String> addTpDenuncia(@RequestBody TipoDenunciaDTO body)
	{
		tipoDenunciaService.adicionarTipoDenuncia(body.getTipoDenuncia());
		return ResponseEntity.status(HttpStatus.OK).body("Tipo de denúncia cadastrado com sucesso.");   
	}
	
	@PutMapping("/attTpDenuncia/{id}")
    public ResponseEntity<String> updateTpDenuncia(@PathVariable Long id, @RequestBody TipoDenunciaDTO body) 
	{
		tipoDenunciaService.atualizarTipoDenuncia(id, body.getTipoDenuncia());
		return ResponseEntity.status(HttpStatus.OK).body("Tipo de denúncia atualizado com sucesso.");
    }
	
	 @DeleteMapping("excluir/{id}")
	 public ResponseEntity<String> deleteTpDenuncia(@PathVariable Long id) 
	 {
		 tipoDenunciaService.deletarTipoDenuncia(id);
		 return ResponseEntity.status(HttpStatus.OK).body("Tipo de denúncia excluído com sucesso.");
	 }
}