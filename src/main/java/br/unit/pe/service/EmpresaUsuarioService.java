package br.unit.pe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.exception.DataInvalidaException;
import br.unit.pe.model.EmpresaUsuario;
import br.unit.pe.repository.EmpresaUsuarioRepository;

@Service
public class EmpresaUsuarioService {

	@Autowired
	private EmpresaUsuarioRepository repository;
	
	public List<EmpresaUsuario> listar() {
		return repository.findAll();
	}

	public EmpresaUsuario salvar(EmpresaUsuario empresaUsuario) {
		
		if(empresaUsuario.getDataIni().compareTo(new Date())>0) {
			throw new DataInvalidaException(empresaUsuario.getDataIni(),"A data de início precisa ser menor ou igual à data atual:Data de início:");
		}
		if(empresaUsuario.getDataFim().compareTo(new Date())>0) {
			throw new DataInvalidaException(empresaUsuario.getDataFim(),"A data final precisa ser menor ou igual à data atual:Data final:");
		}
		if(empresaUsuario.getDataFim().compareTo(empresaUsuario.getDataIni())<0) {
			throw new DataInvalidaException(empresaUsuario.getDataFim(),empresaUsuario.getDataIni(),"A data final precisa ser maior ou igual à data inicial.Data final:");
		}
		return repository.save(empresaUsuario);
	}

	public List<EmpresaUsuario> listByIdUsuario(Long idUsuario) {
		return repository.listByIdUsuario(idUsuario);
	}
}
