package br.com.jhowsoftware.resgateanimal.dtos;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Coloracao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ColoracaoDTO implements Serializable
{
	private Long idColoracao;
	private String coloracao;
	
	public ColoracaoDTO(Coloracao entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}