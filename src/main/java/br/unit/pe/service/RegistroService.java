package br.unit.pe.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
	public List<TecnologiaUsuario> listTecUsuByIdUsuario(Long idUsuario) {
		return tecnologiaUsuarioService.listByIdUsuario(idUsuario);
	}
	public List<EmpresaUsuario> listEmpUsuByIdUsuario(Long idUsuario) {
		return empresaUsuarioService.listByIdUsuario(idUsuario);
	}
	public List<EmpresaUsuarioItem> listEmpUsuItemByIdUsuario(Long userId) {
		return empresaUsuarioItemService.listByIdUsuario(userId);
	}
	@Transactional
	public Map<String,Object> salvarRegistro(Map<String,Object> objetos) {
		Usuario u = (Usuario) objetos.getOrDefault("usuario",null);
		Usuario usuarioBd = null;
		if(u!=null) {
			usuarioBd = salvar(u);
		}
		List<Empresa> e = (ArrayList<Empresa>) objetos.getOrDefault("empresas",null);
		//List<Empresa> e2 = new ArrayList<>();
		if(e!=null) {
			for (Empresa empresa : e) {
				//e2.add(salvar(empresa));
				salvar(empresa);
			}
		}
		List<Tecnologia> t = (ArrayList<Tecnologia>) objetos.getOrDefault("tecnologias",null);
		//List<Tecnologia> t2 = new ArrayList<>();
		if(t!=null) {
			for (Tecnologia tecnologia : t) {
				//t2.add(salvar(tecnologia));
				salvar(tecnologia);
			}
		}
		List<EmpresaUsuario> euList = (ArrayList<EmpresaUsuario>) objetos.getOrDefault("empresas usuario",null);
		List<EmpresaUsuario> euList2 = new ArrayList<>();
		if(euList!=null) {
			for (EmpresaUsuario empresaUsuario : euList) {
				euList2.add(salvar(empresaUsuario));
			}
		}
		List<EmpresaUsuarioItem> euiList = (ArrayList<EmpresaUsuarioItem>) objetos.getOrDefault("empresa usuario itens",null);
		List<EmpresaUsuarioItem> euiList2 = new ArrayList<>();
		String descricaoEui = null;
		if(euiList!=null) {
			for (EmpresaUsuarioItem empresaUsuarioItem : euiList) {
				System.out.println("Log EUI");
				if(empresaUsuarioItem.getTecnologia().getId()==null)
					descricaoEui =empresaUsuarioItem.getTecnologia().getDescricao();
					for (Tecnologia tecnologia : t) {
						if(tecnologia.getDescricao().equals(descricaoEui)) {
							System.out.println("Id Tecnologia salvo no bd : " +tecnologia.getId());
							empresaUsuarioItem.setTecnologia(tecnologia);
						}
					}
				euiList2.add(salvar(empresaUsuarioItem));
			}
		}
		List<TecnologiaUsuario> tuList = (ArrayList<TecnologiaUsuario>) objetos.getOrDefault("tecnologias usuario",null);
		List<TecnologiaUsuario> tuList2 = new ArrayList<>();
		if(tuList!=null) {
			for (TecnologiaUsuario tecnologiaUsuario : tuList) {
				tuList2.add(salvar(tecnologiaUsuario));
			}
		}
		Map<String,Object> objetos2 = new HashMap<String,Object>();
		objetos2.put("usuario",usuarioBd);
		//objetos2.put("empresas", e2);
		//objetos2.put("tecnologias", t2);
		objetos2.put("empresas usuario", euList2);
		objetos2.put("empresa usuario itens", euiList2);
		objetos2.put("tecnologias usuario", tuList2);
		
		//if(true)throw new RuntimeException();
		return objetos2;
	}
}
