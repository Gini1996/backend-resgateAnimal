package br.com.jhowsoftware.resgateanimal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Pelo")
public class Pelo 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idPelo;
	
	@Column(columnDefinition = "TEXT")
	private String pelo;
}
