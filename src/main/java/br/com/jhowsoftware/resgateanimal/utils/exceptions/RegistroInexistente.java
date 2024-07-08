package br.com.jhowsoftware.resgateanimal.utils.exceptions;

public class RegistroInexistente extends RuntimeException 
{
	private static final long serialVersionUID = -2271372566686907876L;
	
	public RegistroInexistente(String msg)
	{
		super(msg);
	}
}