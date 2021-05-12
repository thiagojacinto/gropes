package br.unit.pe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.unit.pe.model.Tecnologia;
import br.unit.pe.repository.TecnologiaRepository;

@Service
public class TecnologiaService {

	@Autowired
	private TecnologiaRepository repository;

	public Tecnologia salvar(Tecnologia t) {
		return repository.save(t);
	}

	public Optional<Tecnologia> findByDescricao(String descricao) {
		return repository.findByDescricao(descricao);
	}
	
	
}
