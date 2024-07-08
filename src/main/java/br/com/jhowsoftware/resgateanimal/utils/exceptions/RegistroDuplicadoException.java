package br.com.jhowsoftware.resgateanimal.utils.exceptions;

public class RegistroDuplicadoException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public RegistroDuplicadoException(String msg) 
	{
		super(msg);
    }
}