package br.com.jhowsoftware.resgateanimal.utils;

import org.apache.commons.lang3.StringUtils;

import br.com.jhowsoftware.resgateanimal.exceptions.ValorDivergenteException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class ServiceUtils 
{
	public boolean validaString(String body)
	{
		if(StringUtils.isNumeric(body))
			throw new ValorDivergenteException("Valor informado não corresponde com o tipo de campo solicitado pela aplicação");
			
		return false;
	}
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void reiniciarSequencia(String id, String tabela) 
	{
		String queryObterSequencia = getQueryObterSequencia(id, tabela);
		String sequence = (String) entityManager.createNativeQuery(queryObterSequencia)
	                                            .getSingleResult();
		
		if (sequence != null) 
		{
			Integer ultimoIdInt = (Integer) entityManager.createNativeQuery("SELECT MAX(" + id + ") FROM " + tabela)
	                                                     .getSingleResult();
	            
	        Long ultimoId = ultimoIdInt != null ? ultimoIdInt.longValue() : 0L;

	        entityManager.createNativeQuery("ALTER SEQUENCE " + sequence + " RESTART WITH " + (ultimoId + 1))
	                     .executeUpdate();
	    }
	}

	private String getQueryObterSequencia(String id, String tabela) 
	{
		return "SELECT pg_get_serial_sequence('" + tabela + "', '" + id + "')";
	}
}