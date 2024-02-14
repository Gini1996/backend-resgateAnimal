package br.com.jhowsoftware.resgateanimal.dtos;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Condicao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CondicaoDTO 
{
	private Long idCondicao;
	private String condicao;
	
	public CondicaoDTO(Condicao entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}