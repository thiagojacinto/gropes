package br.unit.pe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.exception.EmpresaJaExistenteException;
import br.unit.pe.model.Empresa;
import br.unit.pe.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;
	
	public List<Empresa> listar() {
		
		return repository.findAll();
	}

	public Empresa salvar(Empresa empresa) {
		if(repository.findByDescricao(empresa.getDescricao()).isPresent()) {
			throw new EmpresaJaExistenteException(empresa.getDescricao());
		}
		return repository.save(empresa);
	}
	public Optional<Empresa> findByDescricao(String descricao) {
		return repository.findByDescricao(descricao);
	}
}
