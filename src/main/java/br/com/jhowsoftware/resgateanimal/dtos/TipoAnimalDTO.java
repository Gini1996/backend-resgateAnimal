package br.com.jhowsoftware.resgateanimal.dtos;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.TipoAnimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoAnimalDTO 
{
	private Long idTipoAnimal;
	private String tipoAnimal;
	
	public TipoAnimalDTO(TipoAnimal entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}