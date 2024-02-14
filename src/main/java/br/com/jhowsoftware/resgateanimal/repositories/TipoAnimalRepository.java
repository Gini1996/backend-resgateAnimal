package br.com.jhowsoftware.resgateanimal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhowsoftware.resgateanimal.entities.TipoAnimal;

public interface TipoAnimalRepository extends JpaRepository<TipoAnimal, Long>
{

}
