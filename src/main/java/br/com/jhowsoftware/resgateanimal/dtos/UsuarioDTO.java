package br.com.jhowsoftware.resgateanimal.dtos;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO 
{
	private Long idUsuario;
	private String usuario;
	private String nome;
	private String senha;
	private String cpf;
	private Integer telefone;
	private Date dataNascimento;
	private String email;
	private Integer idTipoUsuario;
	
	public UsuarioDTO(Usuario entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}