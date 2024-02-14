package br.com.jhowsoftware.resgateanimal.dtos;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Pelo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PeloDTO
{
	private Long idPelo;
	private String pelo;
	
	public PeloDTO(Pelo entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}