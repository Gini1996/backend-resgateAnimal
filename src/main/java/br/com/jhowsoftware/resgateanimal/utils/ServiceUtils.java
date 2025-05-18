package br.com.jhowsoftware.resgateanimal.utils;

import org.apache.commons.lang3.StringUtils;

import br.com.jhowsoftware.resgateanimal.utils.exceptions.ValorDivergenteException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtils 
{
	private static final Logger LOG_TECNICO = LoggerFactory.getLogger(ServiceUtils.class);

	public ServiceUtils(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public void validaString(String body)
	{
		if(StringUtils.isNumeric(body))
		{
			LOG_TECNICO.info("O body informado: {} está com o tipo incorreto", body);
			throw new ValorDivergenteException("Valor informado não corresponde com o tipo de campo solicitado pela aplicação");
		}
	}
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void reiniciarSequencia(String id, String tabela) 
	{
		LOG_TECNICO.info("Obtendo sequência para a tabela: {}", tabela);
		String queryObterSequencia = getQueryObterSequencia(id, tabela);
		String sequence = (String) entityManager.createNativeQuery(queryObterSequencia)
	                                            .getSingleResult();

		LOG_TECNICO.info("Verificando se a sequência: {} existe", sequence);
		if (sequence != null) 
		{
			LOG_TECNICO.info("Obtendo o último ID da tabela: {}", tabela);
			Integer ultimoIdInt = (Integer) entityManager.createNativeQuery("SELECT MAX(" + id + ") FROM " + tabela)
	                                                     .getSingleResult();
	            
	        long ultimoId = ultimoIdInt != null ? ultimoIdInt.longValue() : 0L;

			LOG_TECNICO.info("Reiniciando a sequência: {} com o valor: {}", sequence, ultimoId + 1);
	        entityManager.createNativeQuery("ALTER SEQUENCE " + sequence + " RESTART WITH " + (ultimoId + 1))
	                     .executeUpdate();
			LOG_TECNICO.info("Sequência reiniciada com sucesso");
	    }
	}

	private String getQueryObterSequencia(String id, String tabela) 
	{
		LOG_TECNICO.info("Obtendo sequencial para a tabela: {}", tabela);
		return "SELECT pg_get_serial_sequence('" + tabela + "', '" + id + "')";
	}
}