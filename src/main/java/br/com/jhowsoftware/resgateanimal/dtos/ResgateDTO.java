package br.com.jhowsoftware.resgateanimal.dtos;

import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Resgate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResgateDTO 
{
	private Long idResgate;
	private String descricao;
	private Integer idUsuario;
	private Integer idDenuncia;
	private Integer idTipoAnimal;
	private Integer idCondicao;
	private Integer idPorte;
	private Integer idColoracao;
	
	public ResgateDTO(Resgate entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}