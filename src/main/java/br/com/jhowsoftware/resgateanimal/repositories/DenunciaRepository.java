package br.com.jhowsoftware.resgateanimal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhowsoftware.resgateanimal.entities.Denuncia;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long>
{

}