package br.com.jhowsoftware.resgateanimal.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class UsuarioDTO implements Serializable
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