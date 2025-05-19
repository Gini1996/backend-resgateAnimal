package br.com.jhowsoftware.resgateanimal.controllers;

import br.com.jhowsoftware.resgateanimal.dtos.TipoUsuarioDTO;
import br.com.jhowsoftware.resgateanimal.services.RabbitMQService;
import br.com.jhowsoftware.resgateanimal.services.TipoUsuarioService;
import br.com.jhowsoftware.resgateanimal.utils.ControllerUtils;
import br.com.jhowsoftware.resgateanimal.utils.RabbitMQConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/tipousuario")
@Tag(name = "Tipos de Usuários", description = "Endpoints para gerenciar tipos de usuário")
public class TipoUsuarioController extends ControllerUtils
{
	private static final Logger LOG_TECNICO = LoggerFactory.getLogger(TipoUsuarioController.class);

	@Autowired
	private TipoUsuarioService tipoUsuarioService;

	@Autowired
	private RabbitMQService rabbitMQService;

	@Operation(summary = "Busca todos os tipos de usuários", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum tipo de usuário encontrado")
	})
	@GetMapping
	public ResponseEntity<ControllerUtils.ResponseAPI<List<TipoUsuarioDTO>>> findAll()
	{
		LOG_TECNICO.info("Buscando todos os tipos de usuário");
		List<TipoUsuarioDTO> usuarios =  tipoUsuarioService.findAll();
		return usuarios != null ?
				ResponseEntity.ok(responseSucesso("200",usuarios,null)):
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErro("400",null,"Nenhum tipo de usuário encontrado."));
	}

	@Operation(summary = "Busca os tipos de usuário por ID", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "404", description = "O ID: não foi localizado no banco de dados")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseAPI<TipoUsuarioDTO>> findById(@PathVariable Long id)
	{
		LOG_TECNICO.info("Buscando tipo de usuário com ID: {} ", id);
		TipoUsuarioDTO result = tipoUsuarioService.findById(id);
		return result != null ?
				ResponseEntity.ok(responseSucesso("200",result,null)):
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErro("404",null,"O ID: " + id + " não foi localizado no banco de dados."));
	}

	@Operation(summary = "Adiciona um novo tipo de usuário", method = "POST")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Sucesso"),
			@ApiResponse(responseCode = "400", description = "O tipo de usuário já se encontra cadastrado no banco de dados")
	})
	@PostMapping("/addTipoUsuario")
	public ResponseEntity<ResponseAPI<TipoUsuarioDTO>> addTippUsuario(@Valid @RequestBody TipoUsuarioDTO body)
	{
		LOG_TECNICO.info("Adicionando novo tipo de usuário");
		TipoUsuarioDTO result  = tipoUsuarioService.adicionarTipoUsuario(body.getTipoUsuario());

		LOG_TECNICO.info("Enviando mensagem de adição de tipo de usuário para a fila de tipo de usuário");
		this.rabbitMQService.enviarMensagem(RabbitMQConstants.FILA_TIPO_USUARIO,result);
		LOG_TECNICO.info("Tipo de denúncia adicionado com sucesso");

		return ResponseEntity.status(HttpStatus.CREATED).body(responseSucesso("201",result,null));
	}

	@Operation(summary = "Atualiza um tipo de usuário por ID",  method = "PUT")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado com o ID")
	})
	@PutMapping("/attTipoUsuario/{id}")
	public ResponseEntity<ResponseAPI<TipoUsuarioDTO>> updateTipoUsuario(@PathVariable Long id, @Valid @RequestBody TipoUsuarioDTO body)
	{
		LOG_TECNICO.info("Atualizando tipo de usuário com ID: {} ", id);
		TipoUsuarioDTO result = tipoUsuarioService.atualizarTipoUsuario(id, body.getTipoUsuario());

		LOG_TECNICO.info("Enviando mensagem de atualização para a fila de tipo de usuário");
		this.rabbitMQService.enviarMensagem(RabbitMQConstants.FILA_TIPO_USUARIO,result);
		LOG_TECNICO.info("Tipo de usuário atualizado com sucesso");

		return ResponseEntity.status(HttpStatus.OK).body(responseSucesso("200",result,null));
	}

	@Operation(summary = "Deleta um tipo de usuário por ID",  method = "DELETE")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "404", description = "O ID não foi localizado no banco de dados")
	})
	@DeleteMapping("excluir/{id}")
	public ResponseEntity<ResponseAPI<Object>> deleteTipoUsuario(@PathVariable Long id)
	{
		LOG_TECNICO.info("Deletando tipo de usuário com ID: {} ", id);
		tipoUsuarioService.deletarTipoUsuario(id);
		LOG_TECNICO.info("Tipo de usuário com ID: {} deletado com sucesso", id);

		return ResponseEntity.status(HttpStatus.OK).body(responseSucesso("200",null,"Tipo Usuário ID: " + id + " excluída com sucesso"));
	}
}