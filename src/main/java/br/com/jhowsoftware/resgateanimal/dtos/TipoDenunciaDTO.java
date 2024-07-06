package br.com.jhowsoftware.resgateanimal.dtos;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoDenunciaDTO implements Serializable
{
	private Long idTipoDenuncia;
	
	@NotBlank(message = "Tipo de denúncia não pode estar em branco")
	private String tipoDenuncia;
	
	public TipoDenunciaDTO(TipoDenuncia entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}