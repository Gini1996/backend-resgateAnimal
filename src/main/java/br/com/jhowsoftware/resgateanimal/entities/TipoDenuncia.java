package br.com.jhowsoftware.resgateanimal.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tipo_denuncia")
public class TipoDenuncia
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_denuncia")
	private Long idTipoDenuncia;

	@Column(columnDefinition = "TEXT")
	private String tipoDenuncia;
}