package br.com.jhowsoftware.resgateanimal.utils.exceptions;

public class ValorDivergenteException extends RuntimeException 
{
	private static final long serialVersionUID = -8797580003622172156L;
	
	public ValorDivergenteException(String msg)
	{
		super(msg);
	}
}
