package br.com.jhowsoftware.resgateanimal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Resgate")
public class Resgate 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idResgate;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	@ManyToOne
	private Usuario idUsuario;
	
	@ManyToOne
	private Denuncia idDenuncia;
	
	@ManyToOne
	private TipoAnimal idTipoAnimal;
	
	@ManyToOne
	private Condicao idCondicao;
	
	@ManyToOne
	private Porte idPorte;
	
	@ManyToOne
	private Coloracao idColoracao;
}
