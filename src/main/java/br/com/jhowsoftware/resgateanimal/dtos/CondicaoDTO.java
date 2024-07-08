package br.com.jhowsoftware.resgateanimal.dtos;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Condicao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CondicaoDTO implements Serializable
{
	private Long idCondicao;
	private String condicao;
	
	public CondicaoDTO(Condicao entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}