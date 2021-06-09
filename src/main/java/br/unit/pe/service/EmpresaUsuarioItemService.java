package br.unit.pe.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.exception.DataInvalidaException;
import br.unit.pe.model.EmpresaUsuarioItem;
import br.unit.pe.repository.EmpresaUsuarioItemRepository;

@Service
public class EmpresaUsuarioItemService {

	@Autowired
	private EmpresaUsuarioItemRepository repository;
	
	public List<EmpresaUsuarioItem> listar() {
		
		return repository.findAll();
	}

	public EmpresaUsuarioItem salvar(EmpresaUsuarioItem empresaUsuarioItem) {
		if(empresaUsuarioItem.getDataIni().compareTo(new Date())>0) {
			throw new DataInvalidaException(empresaUsuarioItem.getDataIni(),"A data de início precisa ser menor ou igual à data atual:Data de início:");
		}
		if(empresaUsuarioItem.getDataFim().compareTo(new Date())>0) {
			throw new DataInvalidaException(empresaUsuarioItem.getDataFim(),"A data final precisa ser menor ou igual à data atual:Data final:");
		}
		if(empresaUsuarioItem.getDataFim().compareTo(empresaUsuarioItem.getDataIni())<0) {
			throw new DataInvalidaException(empresaUsuarioItem.getDataFim(),empresaUsuarioItem.getDataIni(),"A data final precisa ser maior ou igual à data inicial.Data final:");
		}
		return repository.save(empresaUsuarioItem);
	}

	public List<EmpresaUsuarioItem> listByIdUsuario(Long userId) {
		return repository.listByIdUsuario(userId);
	}
}
