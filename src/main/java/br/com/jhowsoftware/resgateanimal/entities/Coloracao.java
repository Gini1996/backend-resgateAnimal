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
@Table(name = "Coloracao")
public class Coloracao 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idColoracao;
	
	@Column(columnDefinition = "TEXT")
	private String coloracao;
}