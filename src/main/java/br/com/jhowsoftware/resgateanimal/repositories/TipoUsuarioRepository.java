package br.com.jhowsoftware.resgateanimal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhowsoftware.resgateanimal.entities.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long>
{

}