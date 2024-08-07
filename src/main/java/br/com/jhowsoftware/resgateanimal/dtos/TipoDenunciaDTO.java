package br.com.jhowsoftware.resgateanimal.dtos;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TipoDenunciaDTO implements Serializable
{
	@Schema(hidden = true)
	private Long idTipoDenuncia;
	
	@NotBlank(message = "Tipo de denúncia não pode estar em branco")
	private String tipoDenuncia;

	public TipoDenunciaDTO(TipoDenuncia entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}