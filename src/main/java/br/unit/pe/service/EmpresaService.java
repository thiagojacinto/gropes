package br.unit.pe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.entity.Empresa;
import br.unit.pe.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;
	
	public List<Empresa> listar() {
		
		return repository.findAll();
	}
}
