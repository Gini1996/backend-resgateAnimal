package br.com.jhowsoftware.resgateanimal.controllers;

import java.util.List;

import javax.validation.Valid;

import br.com.jhowsoftware.resgateanimal.services.RabbitMQService;
import br.com.jhowsoftware.resgateanimal.services.TipoDenunciaService;
import br.com.jhowsoftware.resgateanimal.utils.RabbitMQConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.utils.ControllerUtils;

@RestController
@RequestMapping(value = "/tipodenuncia")
@Tag(name = "Tipos de Denúncia", description = "Endpoints para gerenciar tipos de denúncia")
public class TipoDenunciaController extends ControllerUtils
{
	@Autowired
	private TipoDenunciaService tipoDenunciaService;

	@Autowired
	private RabbitMQService rabbitMQService;

	@Operation(summary = "Busca todos os tipos de denúncia", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
							@ApiResponse(responseCode = "404", description = "Nenhum tipo de denúncia encontrado")
	})
	@GetMapping
	public ResponseEntity<ResponseAPI<List<TipoDenunciaDTO>>> findAll()
	{
		List<TipoDenunciaDTO> denuncias =  tipoDenunciaService.findAll();
		return denuncias != null ? 
				ResponseEntity.ok(responseSucesso("200",denuncias,null)): 
					ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErro("400",null,"Nenhum tipo de denúncia encontrado."));
	}

	@Operation(summary = "Busca os tipos de denúncia por ID", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
							@ApiResponse(responseCode = "404", description = "O ID: não foi localizado no banco de dados")
	})
	@GetMapping(value = "/{id}")
    public ResponseEntity<ResponseAPI<TipoDenunciaDTO>> findById(@PathVariable Long id)
	{
        TipoDenunciaDTO result = tipoDenunciaService.findById(id);
        return result != null ?
				ResponseEntity.ok(responseSucesso("200",result,null)):
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErro("404",null,"O ID: " + id + " não foi localizado no banco de dados."));
    }

	@Operation(summary = "Adiciona um novo tipo de denúncia", method = "POST")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Sucesso"),
							@ApiResponse(responseCode = "400", description = "O tipo de denuncia já se encontra cadastrado no banco de dados")
	})
	@PostMapping("/addTpDenuncia")
	public ResponseEntity<ResponseAPI<TipoDenunciaDTO>> addTpDenuncia(@Valid @RequestBody TipoDenunciaDTO body)
	{
		TipoDenunciaDTO result  = tipoDenunciaService.adicionarTipoDenuncia(body.getTipoDenuncia());
		this.rabbitMQService.enviarMensagem(RabbitMQConstants.FILA_TIPO_DENUNCIA,result);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseSucesso("201",result,null));
	}

	@Operation(summary = "Atualiza um tipo de denúncia por ID",  method = "PUT")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
							@ApiResponse(responseCode = "404", description = "Tipo de denúncia não encontrado com o ID")
	})
	@PutMapping("/attTpDenuncia/{id}")
    public ResponseEntity<ResponseAPI<TipoDenunciaDTO>> updateTpDenuncia(@PathVariable Long id, @Valid @RequestBody TipoDenunciaDTO body) 
	{
		TipoDenunciaDTO result = tipoDenunciaService.atualizarTipoDenuncia(id, body.getTipoDenuncia());
		this.rabbitMQService.enviarMensagem(RabbitMQConstants.FILA_TIPO_DENUNCIA,result);
		return ResponseEntity.status(HttpStatus.OK).body(responseSucesso("200",result,null));
    }

	@Operation(summary = "Deleta um tipo de denúncia por ID",  method = "DELETE")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
							@ApiResponse(responseCode = "404", description = "O ID não foi localizado no banco de dados")
	})
	@DeleteMapping("excluir/{id}")
	public ResponseEntity<ResponseAPI<Object>> deleteTpDenuncia(@PathVariable Long id)
	{
		tipoDenunciaService.deletarTipoDenuncia(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseSucesso("200",null,"Denuncia ID: " + id + " excluída com sucesso"));
	}
}