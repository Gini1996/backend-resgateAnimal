package br.com.jhowsoftware.resgateanimal.dtos;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoDenunciaDTO 
{
	private Long idTipoDenuncia;
	private String tipoDenuncia;
	
	public TipoDenunciaDTO(TipoDenuncia entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}