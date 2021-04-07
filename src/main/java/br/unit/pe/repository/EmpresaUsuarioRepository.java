package br.unit.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unit.pe.entity.EmpresaUsuario;

@Repository
public interface EmpresaUsuarioRepository extends JpaRepository<EmpresaUsuario, Long> {

}
