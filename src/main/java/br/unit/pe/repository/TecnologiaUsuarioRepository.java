package br.unit.pe.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.unit.pe.model.TecnologiaUsuario;

@Repository
public interface TecnologiaUsuarioRepository extends JpaRepository<TecnologiaUsuario, Long> {

	@Query(value = "SELECT tu FROM TecnologiaUsuario tu where tu.conheceDesde = (select min(tu2.conheceDesde) from TecnologiaUsuario tu2)")
	public Date minConheceDesde();
}
