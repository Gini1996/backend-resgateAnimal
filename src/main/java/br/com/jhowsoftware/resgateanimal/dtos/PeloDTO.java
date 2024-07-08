package br.com.jhowsoftware.resgateanimal.dtos;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Pelo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PeloDTO implements Serializable
{
	private Long idPelo;
	private String pelo;
	
	public PeloDTO(Pelo entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}