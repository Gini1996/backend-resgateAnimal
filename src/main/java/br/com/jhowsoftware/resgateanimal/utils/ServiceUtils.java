package br.com.jhowsoftware.resgateanimal.utils;

import org.apache.commons.lang3.StringUtils;

import br.com.jhowsoftware.resgateanimal.exceptions.ValorDivergenteException;

public class ServiceUtils 
{
	public boolean validaString(String body)
	{
		if(StringUtils.isNumeric(body))
			throw new ValorDivergenteException("Valor informado não corresponde com o tipo de campo solicitado pela aplicação");
			
		return false;
	}
}