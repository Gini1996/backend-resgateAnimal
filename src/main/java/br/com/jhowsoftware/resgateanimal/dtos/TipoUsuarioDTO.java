package br.com.jhowsoftware.resgateanimal.dtos;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.TipoUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoUsuarioDTO 
{
	private Long idTipoUsuario;
	private String tipoUsuario;
	
	public TipoUsuarioDTO(TipoUsuario entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}