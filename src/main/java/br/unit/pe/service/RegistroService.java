package br.unit.pe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.model.Empresa;
import br.unit.pe.model.EmpresaUsuario;
import br.unit.pe.model.EmpresaUsuarioItem;
import br.unit.pe.model.Tecnologia;
import br.unit.pe.model.TecnologiaUsuario;
import br.unit.pe.model.Usuario;

@Service
public class RegistroService {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private EmpresaUsuarioService empresaUsuarioService;
	@Autowired
	private EmpresaUsuarioItemService empresaUsuarioItemService;
	@Autowired
	private TecnologiaService tecnologiaService;
	@Autowired
	private TecnologiaUsuarioService tecnologiaUsuarioService;
	
	public Usuario salvar(Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	public Empresa salvar(Empresa empresa) {
		return empresaService.salvar(empresa);
	}
	public EmpresaUsuario salvar(EmpresaUsuario empresaUsuario) {
		return empresaUsuarioService.salvar(empresaUsuario);
	}
	public Empresa findEmpresaByDescricao(String descricao) {
		return empresaService.findByDescricao(descricao).orElse(null);
	}
	public Tecnologia findTecnologiaByDescricao(String descricao) {
		return tecnologiaService.findByDescricao(descricao).orElse(null);
	}
	public EmpresaUsuarioItem salvar(EmpresaUsuarioItem eui) {
		return empresaUsuarioItemService.salvar(eui);
	}
	public Tecnologia salvar(Tecnologia t) {
		return tecnologiaService.salvar(t);
	}
	public TecnologiaUsuario salvar(TecnologiaUsuario tecnologiaUsuario) {
		return tecnologiaUsuarioService.salvar(tecnologiaUsuario);
	}
}
