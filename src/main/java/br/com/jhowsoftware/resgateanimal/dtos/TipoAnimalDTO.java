package br.com.jhowsoftware.resgateanimal.dtos;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.TipoAnimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TipoAnimalDTO implements Serializable
{
	private Long idTipoAnimal;
	private String tipoAnimal;
	
	public TipoAnimalDTO(TipoAnimal entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}