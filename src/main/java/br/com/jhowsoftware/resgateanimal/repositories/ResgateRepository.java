package br.com.jhowsoftware.resgateanimal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhowsoftware.resgateanimal.entities.Resgate;

public interface ResgateRepository extends JpaRepository<Resgate, Long>
{

}
