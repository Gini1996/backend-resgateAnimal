package br.com.jhowsoftware.resgateanimal.dtos;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Coloracao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColoracaoDTO 
{
	private Long idColoracao;
	private String coloracao;
	
	public ColoracaoDTO(Coloracao entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}