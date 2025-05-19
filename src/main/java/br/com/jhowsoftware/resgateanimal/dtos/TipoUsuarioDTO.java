package br.com.jhowsoftware.resgateanimal.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.TipoUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class TipoUsuarioDTO implements Serializable
{
	@Schema(hidden = true)
	private Long idTipoUsuario;

	@NotBlank(message = "Tipo de usuário não pode estar em branco")
	private String tipoUsuario;
	
	public TipoUsuarioDTO(TipoUsuario entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}