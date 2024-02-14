package br.com.jhowsoftware.resgateanimal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhowsoftware.resgateanimal.entities.Pelo;

public interface PeloRepository extends JpaRepository<Pelo, Long>
{

}
