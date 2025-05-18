package br.com.jhowsoftware.resgateanimal.services.impl;


import java.util.List;

import br.com.jhowsoftware.resgateanimal.services.TipoDenunciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroDuplicadoException;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroInexistente;
import br.com.jhowsoftware.resgateanimal.repositories.TipoDenunciaRepository;
import br.com.jhowsoftware.resgateanimal.utils.ServiceUtils;

@Service
public class TipoDenunciaServiceImpl implements TipoDenunciaService
{
	private static final Logger LOG_TECNICO = LoggerFactory.getLogger(TipoDenunciaServiceImpl.class);

	@Autowired
	private TipoDenunciaRepository tipoDenunciaRepository;

	@Autowired
	private ServiceUtils serviceUtils;

	@Transactional(readOnly = true)
	public List<TipoDenunciaDTO> findAll() throws RegistroInexistente
    {
	    try 
	    {
	        List<TipoDenuncia> resultTpDenuncia = tipoDenunciaRepository.findAll();
			LOG_TECNICO.info("Todos os tipos de denúncia localizados com sucesso");
            return resultTpDenuncia.stream().map(TipoDenunciaDTO::new).toList();
	    } 
	    catch (Exception e) 
	    {
			LOG_TECNICO.error("Erro ao buscar todos os tipos de denúncia: ", e);
	        throw new RegistroInexistente("Não existem denuncias cadastradas");
	    }
	}
	
	@Transactional(readOnly = true)
	public TipoDenunciaDTO findById(Long id)
	{
		TipoDenuncia result = tipoDenunciaRepository.findById(id)
							  .orElseThrow(() -> new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados"));
		LOG_TECNICO.info("Tipo de denúncia com ID: {} localizado com sucesso", id);
	    return new TipoDenunciaDTO(result);
	}
	
	@Transactional
	public void deletarTipoDenuncia(Long id)
	{
		final String idTabela = "id_tipo_denuncia";
		final String tabela = "tipo_denuncia";

		LOG_TECNICO.info("Verificando se o ID: {} existe no banco de dados", id);
        if(!tipoDenunciaRepository.existsById(id))
        	throw new RegistroInexistente("O ID: " + id + " não foi localizado no banco de dados");

		LOG_TECNICO.info("Deletando o tipo de denúncia com ID: {}", id);
        tipoDenunciaRepository.deleteById(id);
		LOG_TECNICO.info("Tipo de denúncia com ID: {} deletado com sucesso", id);

		LOG_TECNICO.info("Reiniciando a sequência do ID: {} da tabela: {}", idTabela, tabela);
        serviceUtils.reiniciarSequencia(idTabela,tabela);
		LOG_TECNICO.info("Sequência do ID: {} da tabela: {} reiniciada com sucesso", idTabela, tabela);
	}
	
	@Transactional
	public TipoDenunciaDTO adicionarTipoDenuncia(String tpDenuncia) 
	{
		LOG_TECNICO.info("Validando o tipo de dado: {} para adicionar a lista", tpDenuncia);
		serviceUtils.validaString(tpDenuncia);

		LOG_TECNICO.info("Verificando se o tipo de denúncia: {} já está cadastrado", tpDenuncia);
        if (tipoDenunciaRepository.existsByTipoDenuncia(tpDenuncia))
		{
			LOG_TECNICO.info("Tipo de Denúncia já se encontra cadastrado no banco de dados");
			throw new RegistroDuplicadoException("O tipo de denuncia: " + tpDenuncia + " já se encontra cadastrado no banco de dados");
		}

		LOG_TECNICO.info("Setando o tipo de denúncia: {}", tpDenuncia);
        TipoDenuncia tipoDenuncia = new TipoDenuncia();
        tipoDenuncia.setTipoDenuncia(tpDenuncia);
		LOG_TECNICO.info("Tipo de denúncia: {} setando com sucesso", tpDenuncia);

		LOG_TECNICO.info("Salvando o tipo de denúncia: {}", tpDenuncia);
        TipoDenuncia save = tipoDenunciaRepository.save(tipoDenuncia);
		LOG_TECNICO.info("Tipo de denúncia: {} salvo com sucesso", tpDenuncia);

		LOG_TECNICO.info("Gerando o DTO do tipo de denúncia: {}", tpDenuncia);
        TipoDenunciaDTO tipoDenunciaDTO = new TipoDenunciaDTO();
        tipoDenunciaDTO.setIdTipoDenuncia(save.getIdTipoDenuncia());
        tipoDenunciaDTO.setTipoDenuncia(save.getTipoDenuncia());
		LOG_TECNICO.info("DTO do tipo de denúncia: {} gerado com sucesso", tpDenuncia);

        return tipoDenunciaDTO;
	}
	
	@Transactional
	public TipoDenunciaDTO atualizarTipoDenuncia(Long id, String tpDenuncia)
	{
		LOG_TECNICO.info("Validando o tipo de dado: {} para atualização", tpDenuncia);
		serviceUtils.validaString(tpDenuncia);

		LOG_TECNICO.info("Localizando o tipo de denúncia com o ID: {}", id);
		TipoDenuncia tipoDenuncia = tipoDenunciaRepository.findById(id)
									.orElseThrow(() -> new RegistroInexistente("Tipo de denúncia não encontrado com o ID: " + id));

		LOG_TECNICO.info("Setando o tipo de denúncia: {} para atualização", tpDenuncia);
		tipoDenuncia.setTipoDenuncia(tpDenuncia);

		LOG_TECNICO.info("Atualizando o tipo de denúncia: {}", tpDenuncia);
		TipoDenuncia save = tipoDenunciaRepository.save(tipoDenuncia);

		LOG_TECNICO.info("Gerando o DTO do tipo de denúncia: {} atualizado", tpDenuncia);
		TipoDenunciaDTO tipoDenunciaDTO = new TipoDenunciaDTO();
        tipoDenunciaDTO.setIdTipoDenuncia(save.getIdTipoDenuncia());
        tipoDenunciaDTO.setTipoDenuncia(save.getTipoDenuncia());
		LOG_TECNICO.info("DTO do tipo de denúncia atualizada: {} gerado com sucesso", tpDenuncia);

        return tipoDenunciaDTO;
	}
}