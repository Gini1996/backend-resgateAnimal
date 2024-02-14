package br.com.jhowsoftware.resgateanimal.dtos;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Porte;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PorteDTO 
{
	private Long idPorte;
	private String porte;
	
	public PorteDTO(Porte entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}