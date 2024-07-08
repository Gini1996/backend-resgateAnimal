package br.com.jhowsoftware.resgateanimal.dtos;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.TipoUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TipoUsuarioDTO implements Serializable
{
	private Long idTipoUsuario;
	private String tipoUsuario;
	
	public TipoUsuarioDTO(TipoUsuario entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}