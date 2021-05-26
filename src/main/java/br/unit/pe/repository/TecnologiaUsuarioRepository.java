package br.unit.pe.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.unit.pe.model.TecnologiaUsuario;

@Repository
public interface TecnologiaUsuarioRepository extends JpaRepository<TecnologiaUsuario, Long> {

	@Query(value = "SELECT tu FROM TecnologiaUsuario tu where tu.conheceDesde = (select min(tu2.conheceDesde) from TecnologiaUsuario tu2)")
	public Date minConheceDesde();

	@Query(value = "select tu from TecnologiaUsuario tu inner join tu.usuario u where u.id = :idUsuario")
	public List<TecnologiaUsuario> listByIdUsuario(@Param("idUsuario")Long idUsuario);
}
