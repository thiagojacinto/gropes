package br.unit.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.unit.pe.model.EmpresaUsuario;

@Repository
public interface EmpresaUsuarioRepository extends JpaRepository<EmpresaUsuario, Long> {

	@Query(value = "select eu from EmpresaUsuario eu inner join eu.usuario u where u.id = :idUsuario")
	List<EmpresaUsuario> listByIdUsuario(@Param("idUsuario")Long idUsuario);

}
