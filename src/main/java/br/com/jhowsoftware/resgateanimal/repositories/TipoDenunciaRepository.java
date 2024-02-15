package br.com.jhowsoftware.resgateanimal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;

public interface TipoDenunciaRepository extends JpaRepository<TipoDenuncia, Long>
{
	boolean existsByTipoDenuncia(String tipoDenuncia);
}
