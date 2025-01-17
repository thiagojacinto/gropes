package br.unit.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unit.pe.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
