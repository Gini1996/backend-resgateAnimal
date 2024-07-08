package br.com.jhowsoftware.resgateanimal.entities;

import java.util.Date;

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
@Table(name = "Usuario")
public class Usuario 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Column(columnDefinition = "TEXT")
	private String usuario;

	@Column(columnDefinition = "TEXT")
	private String nome;
	
	@Column(columnDefinition = "TEXT")
	private String senha;
	
	@Column(columnDefinition = "TEXT")
	private String cpf;
	
	private Integer telefone;
	
	private Date dataNascimento;
	
	@Column(columnDefinition = "TEXT")
	private String email;
	
	@ManyToOne
	private TipoUsuario idTipoUsuario;
}
