package br.com.jhowsoftware.resgateanimal.dtos;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import br.com.jhowsoftware.resgateanimal.entities.Denuncia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DenunciaDTO implements Serializable
{
	private Long idDenuncia;
	private String cep;
	private String descricao;
	private String endereco;
	private String referencia;
	private Integer quantidade;
	private Integer idUsuario;
	private Integer idTipoDenuncia;
	private Integer idPelo;
	private Integer idColoracao;
	private Integer idTipoAnimal;
	private Integer idPorte;
	private Integer idCondicao;
	
	public DenunciaDTO(Denuncia entity)
	{
		BeanUtils.copyProperties(entity, this);
	}
}