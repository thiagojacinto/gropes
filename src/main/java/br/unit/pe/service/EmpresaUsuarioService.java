package br.unit.pe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.entity.EmpresaUsuario;
import br.unit.pe.repository.EmpresaUsuarioRepository;

@Service
public class EmpresaUsuarioService {

	@Autowired
	private EmpresaUsuarioRepository repository;
	
	public List<EmpresaUsuario> listar() {
		return repository.findAll();
	}
}
