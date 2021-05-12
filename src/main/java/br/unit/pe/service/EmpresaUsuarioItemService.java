package br.unit.pe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.exception.EmpresaJaExistenteException;
import br.unit.pe.model.Empresa;
import br.unit.pe.model.EmpresaUsuarioItem;
import br.unit.pe.repository.EmpresaRepository;
import br.unit.pe.repository.EmpresaUsuarioItemRepository;

@Service
public class EmpresaUsuarioItemService {

	@Autowired
	private EmpresaUsuarioItemRepository repository;
	
	public List<EmpresaUsuarioItem> listar() {
		
		return repository.findAll();
	}

	public EmpresaUsuarioItem salvar(EmpresaUsuarioItem empresaUsuarioItem) {
		return repository.save(empresaUsuarioItem);
	}
}
