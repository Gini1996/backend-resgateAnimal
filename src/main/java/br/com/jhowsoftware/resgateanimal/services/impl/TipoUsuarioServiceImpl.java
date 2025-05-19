package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.dtos.TipoUsuarioDTO;
import br.com.jhowsoftware.resgateanimal.entities.TipoUsuario;
import br.com.jhowsoftware.resgateanimal.repositories.TipoUsuarioRepository;
import br.com.jhowsoftware.resgateanimal.services.TipoUsuarioService;
import br.com.jhowsoftware.resgateanimal.utils.ServiceUtils;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroDuplicadoException;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroInexistente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService
{
	private static final Logger LOG_TECNICO = LoggerFactory.getLogger(TipoUsuarioServiceImpl.class);

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

	@Autowired
	private ServiceUtils serviceUtils;

	@Override
	public List<TipoUsuarioDTO> findAll()
	{
		try
		{
			List<TipoUsuario> resultTipoUsuario = tipoUsuarioRepository.findAll();
			LOG_TECNICO.info("Todos os tipos de usuários localizados com sucesso");
			return resultTipoUsuario.stream().map(TipoUsuarioDTO::new).toList();
		}
		catch (Exception e)
		{
			LOG_TECNICO.error("Erro ao buscar todos os tipos de usuários: ", e);
			throw new RegistroInexistente("Não existem usuários cadastradas");
		}
	}

	@Transactional(readOnly = true)
	public TipoUsuarioDTO findById(Long id)
	{
		TipoUsuario result = tipoUsuarioRepository.findById(id)
					.orElseThrow(() -> new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados"));

		LOG_TECNICO.info("Tipo de usuário com ID: {} localizado com sucesso", id);
		return new TipoUsuarioDTO(result);
	}

	@Transactional
	public void deletarTipoUsuario(Long id)
	{
		final String idTabela = "id_tipo_usuario";
		final String tabela = "tipo_usuario";

		LOG_TECNICO.info("Verificando se o ID: {} existe no banco de dados", id);
		if(!tipoUsuarioRepository.existsById(id))
			throw new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados");

		LOG_TECNICO.info("Deletando o tipo de usuário com ID: {}", id);
		tipoUsuarioRepository.deleteById(id);
		LOG_TECNICO.info("Tipo de usuário com ID: {} deletado com sucesso", id);

		LOG_TECNICO.info("Reiniciando a sequência do ID: {} da tabela: {}", idTabela, tabela);
		serviceUtils.reiniciarSequencia(idTabela,tabela);
		LOG_TECNICO.info("Sequência do ID: {} da tabela: {} reiniciada com sucesso", idTabela, tabela);
	}

	@Transactional
	public TipoUsuarioDTO adicionarTipoUsuario(String tpUsuario)
	{
		LOG_TECNICO.info("Validando o tipo de dado: {} para adicionar a lista", tpUsuario);
		serviceUtils.validaString(tpUsuario);

		LOG_TECNICO.info("Verificando se o tipo de usuário: {} já está cadastrado", tpUsuario);
		if (tipoUsuarioRepository.existsByTipoUsuario(tpUsuario))
		{
			LOG_TECNICO.info("Tipo de Denúncia já se encontra cadastrado no banco de dados");
			throw new RegistroDuplicadoException("O tipo de usuário: " + tpUsuario + " já se encontra cadastrado no banco de dados");
		}

		LOG_TECNICO.info("Setando o tipo de usuário: {}", tpUsuario);
		TipoUsuario tipoUsuario = new TipoUsuario();
		tipoUsuario.setTipoUsuario(tpUsuario);
		LOG_TECNICO.info("Tipo de usuário: {} setado com sucesso", tpUsuario);

		LOG_TECNICO.info("Salvando o tipo de usuário: {}", tpUsuario);
		TipoUsuario save = tipoUsuarioRepository.save(tipoUsuario);
		LOG_TECNICO.info("Tipo de usuário: {} salvo com sucesso", tpUsuario);

		LOG_TECNICO.info("Gerando o DTO do tipo de usuário: {}", tpUsuario);
		TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
		tipoUsuarioDTO.setIdTipoUsuario(save.getIdTipoUsuario());
		tipoUsuarioDTO.setTipoUsuario(save.getTipoUsuario());
		LOG_TECNICO.info("DTO do tipo de usuário: {} gerado com sucesso", tpUsuario);

		return tipoUsuarioDTO;
	}

	@Transactional
	public TipoUsuarioDTO atualizarTipoUsuario(Long id, String tpUsuario)
	{
		LOG_TECNICO.info("Validando o tipo de dado: {} para atualização", tpUsuario);
		serviceUtils.validaString(tpUsuario);

		LOG_TECNICO.info("Localizando o tipo de usuário com o ID: {}", id);
		TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(id)
					.orElseThrow(() -> new RegistroInexistente("Tipo de denúncia não encontrado com o ID: " + id));

		LOG_TECNICO.info("Setando o tipo de usuário: {} para atualização", tpUsuario);
		tipoUsuario.setTipoUsuario(tpUsuario);

		LOG_TECNICO.info("Atualizando o tipo de usuário: {}", tpUsuario);
		TipoUsuario save = tipoUsuarioRepository.save(tipoUsuario);

		LOG_TECNICO.info("Gerando o DTO do tipo de usuário: {} atualizado", tpUsuario);
		TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
		tipoUsuarioDTO.setIdTipoUsuario(save.getIdTipoUsuario());
		tipoUsuarioDTO.setTipoUsuario(save.getTipoUsuario());
		LOG_TECNICO.info("DTO do tipo de usuário atualizado: {} gerado com sucesso", tpUsuario);

		return tipoUsuarioDTO;
	}
}