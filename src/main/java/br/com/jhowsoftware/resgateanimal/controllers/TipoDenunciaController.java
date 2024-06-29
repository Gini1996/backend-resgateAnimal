package br.com.jhowsoftware.resgateanimal.controllers;

import java.util.List;

import javax.validation.Valid;

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
import br.com.jhowsoftware.resgateanimal.utils.ControllerUtils;

@RestController
@RequestMapping(value = "/tipodenuncia")
public class TipoDenunciaController extends ControllerUtils
{
	@Autowired
	private TipoDenunciaService tipoDenunciaService;

	@GetMapping
	public ResponseEntity<ResponseAPI<List<TipoDenunciaDTO>>> findAll()
	{
		List<TipoDenunciaDTO> denuncias =  tipoDenunciaService.findAll();
		return denuncias != null ? 
				ResponseEntity.ok(responseSucesso("200",denuncias,null)): 
					ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErro("404",null,"Nenhum tipo de denúncia encontrado."));
	}
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<ResponseAPI<TipoDenunciaDTO>> findById(@PathVariable Long id) 
	{
        TipoDenunciaDTO result = tipoDenunciaService.findById(id);
        return ResponseEntity.ok(responseSucesso("200",result,null));
    }
	
	@PostMapping("/addTpDenuncia")
	public ResponseEntity<ResponseAPI<TipoDenunciaDTO>> addTpDenuncia(@Valid @RequestBody TipoDenunciaDTO body)
	{
		TipoDenunciaDTO result  = tipoDenunciaService.adicionarTipoDenuncia(body.getTipoDenuncia());
		return ResponseEntity.status(HttpStatus.CREATED).body(responseSucesso("201",result,null));
	}
	
	@PutMapping("/attTpDenuncia/{id}")
    public ResponseEntity<ResponseAPI<TipoDenunciaDTO>> updateTpDenuncia(@PathVariable Long id, @Valid @RequestBody TipoDenunciaDTO body) 
	{
		TipoDenunciaDTO result = tipoDenunciaService.atualizarTipoDenuncia(id, body.getTipoDenuncia());
		return ResponseEntity.status(HttpStatus.OK).body(responseSucesso("200",result,null));
    }
	
	 @DeleteMapping("excluir/{id}")
	 public ResponseEntity<ResponseAPI<Object>> deleteTpDenuncia(@PathVariable Long id) 
	 {
		 tipoDenunciaService.deletarTipoDenuncia(id);
		 return ResponseEntity.status(HttpStatus.OK).body(responseSucesso("200",null,"Denuncia ID: " + id + " excluída com sucesso"));
	 }
}