package br.unit.pe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.exception.DataInvalidaException;
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

	public TecnologiaUsuario salvar(TecnologiaUsuario tecnologiaUsuario) {
		if(tecnologiaUsuario.getEstudaDesde().compareTo(new Date())>0) {
			throw new DataInvalidaException(tecnologiaUsuario.getEstudaDesde(),"A data de início precisa ser menor ou igual à data atual:Data de início:");
		}
		if(tecnologiaUsuario.getEstudouAte().compareTo(new Date())>0) {
			throw new DataInvalidaException(tecnologiaUsuario.getEstudouAte(),"A data final precisa ser menor ou igual à data atual:Data final:");
		}
		if(tecnologiaUsuario.getEstudouAte().compareTo(tecnologiaUsuario.getEstudaDesde())<0) {
			throw new DataInvalidaException(tecnologiaUsuario.getEstudouAte(),tecnologiaUsuario.getEstudaDesde(),"A data final precisa ser maior ou igual à data inicial.Data final:");
		}
		return tecUsuRepository.save(tecnologiaUsuario);
	}

	public List<TecnologiaUsuario> listByIdUsuario(Long idUsuario) {
		return tecUsuRepository.listByIdUsuario(idUsuario);
	}
	
	
}
