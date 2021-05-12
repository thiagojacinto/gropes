package br.unit.pe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unit.pe.model.Empresa;
import br.unit.pe.model.EmpresaUsuarioItem;

@Repository
public interface EmpresaUsuarioItemRepository extends JpaRepository<EmpresaUsuarioItem, Long> {

}
