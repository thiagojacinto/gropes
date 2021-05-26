package br.unit.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.unit.pe.model.EmpresaUsuarioItem;

@Repository
public interface EmpresaUsuarioItemRepository extends JpaRepository<EmpresaUsuarioItem, Long> {

	@Query(value = "select eui from EmpresaUsuarioItem eui inner join eui.empUsu eu inner join eu.usuario u where u.id = :idUsuario")
	List<EmpresaUsuarioItem> listByIdUsuario(@Param("idUsuario")Long userId);

}
