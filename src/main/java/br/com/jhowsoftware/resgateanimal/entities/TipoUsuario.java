package br.com.jhowsoftware.resgateanimal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TipoUsuario")
public class TipoUsuario 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idTipoUsuario;
	
	@Column(columnDefinition = "TEXT")
	private String tipoUsuario;
}