package br.unit.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unit.pe.entity.Tecnologia;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
}
