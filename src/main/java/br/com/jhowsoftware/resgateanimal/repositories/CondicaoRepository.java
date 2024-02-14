package br.com.jhowsoftware.resgateanimal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhowsoftware.resgateanimal.entities.Condicao;

public interface CondicaoRepository extends JpaRepository<Condicao, Long>
{

}