package br.com.jhowsoftware.resgateanimal.exceptions;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StandardError implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;
}