package br.com.jhowsoftware.resgateanimal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Denuncia")
public class Denuncia 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idDenuncia;
	
	@Column(columnDefinition = "TEXT")
	private String cep;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	@Column(columnDefinition = "TEXT")
	private String endereco;
	
	@Column(columnDefinition = "TEXT")
	private String referencia;
	
	private Integer quantidade;
	
	@ManyToOne
	private Usuario idUsuario;
	
	@ManyToOne
	private TipoDenuncia idTipoDenuncia;
	
	@ManyToOne
	private Pelo idPelo;
	
	@ManyToOne
	private Coloracao idColoracao;
	
	@ManyToOne
	private TipoAnimal idTipoAnimal;
	
	@ManyToOne
	private Porte idPorte;
	
	@ManyToOne
	private Condicao idCondicao;
}
