package br.unit.pe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.model.TecnologiaUsuario;
import br.unit.pe.repository.TecnologiaUsuarioRepository;

@Service
public class TecnologiaUsuarioService {

	@Autowired
	private TecnologiaUsuarioRepository tecUsuRepository;

	public List<TecnologiaUsuario> listar() {

		return tecUsuRepository.findAll();
	}
	
	public Date minConheceDesde() {
		return tecUsuRepository.minConheceDesde();
	}
	
	
}
