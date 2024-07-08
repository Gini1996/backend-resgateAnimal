package br.com.jhowsoftware.resgateanimal.dtos;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Porte;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PorteDTO implements Serializable
{
	private Long idPorte;
	private String porte;
	
	public PorteDTO(Porte entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}