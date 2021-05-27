package br.unit.pe.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.unit.pe.exception.UserNotFoundException;
import br.unit.pe.model.Empresa;
import br.unit.pe.model.EmpresaUsuario;
import br.unit.pe.model.EmpresaUsuarioDTO;
import br.unit.pe.model.EmpresaUsuarioItem;
import br.unit.pe.model.InfoRegistro;
import br.unit.pe.model.Tecnologia;
import br.unit.pe.model.TecnologiaDTO;
import br.unit.pe.model.TecnologiaUsuario;
import br.unit.pe.model.TecnologiaUsuarioDTO;
import br.unit.pe.model.Usuario;
import br.unit.pe.model.UsuarioDTO;
import br.unit.pe.service.RegistroService;
import br.unit.pe.service.UsuarioService;

@RestController
public class UsuarioController {
	@Autowired
	UsuarioService service;
	@Autowired
	RegistroService registroService;
	@Autowired
	private ModelMapper modelMapper;
	//@Autowired
	//private ObjectMapper objectMapper;
	
	@GetMapping("/usuarios")
	Collection<Usuario> listar() {
		return service.listar();
	}

	//@GetMapping("/usuarios/findByNome/{nome}")
	Usuario procurar(@PathVariable("nome") String userName) {
		return service.findByNome(userName).orElseThrow(() -> new UserNotFoundException(userName));
	}

	//@GetMapping("/usuarios/findById/{id}")
	Usuario procurar(@PathVariable("id") Long id) {
		return service.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@DeleteMapping("/usuarios/{id}")
	ResponseEntity<Void> excluir(@PathVariable("id") long userId) {
		Usuario usuario = procurar(userId);
		service.excluir(usuario);
		return ResponseEntity.noContent().build();
	}

	boolean existe(long id) {
		return service.existe(id);
	}

	@PutMapping("/usuarios/{id}")
	ResponseEntity<Void> atualizar(@RequestBody Usuario usuario, @PathVariable("id") long userId) {
		if (existe(userId)) {
			usuario.setId(userId);
			service.salvar(usuario);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/usuarios/login/{email}/{senha}")
	ResponseEntity<Void> login(@PathVariable("email") String email, @PathVariable("senha") String senha) {
		if (service.login(email, senha)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PostMapping(value = "/usuarios", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	ResponseEntity<?> registrar(@RequestBody Map<String, Object> payload) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			UsuarioDTO userRequest = modelMapper.map(payload.get("basico"), UsuarioDTO.class);
			//System.out.println(userRequest);
			Usuario u = modelMapper.map(userRequest, Usuario.class);
			//System.out.println(u);
			u.setRua(userRequest.getEndereco().getRua());
			u.setCep(userRequest.getEndereco().getCep());
			u.setComplemento(userRequest.getEndereco().getComplemento());
			u.setNumero(userRequest.getEndereco().getNumero());
			u.setNascimento(userRequest.getNascimento());
			
			Usuario usuarioBd = registroService.salvar(u);
			System.out.println("Usuário salvo no bd: "+ usuarioBd);
			
			ArrayList<Object> a = (ArrayList) payload.get("profissionais");
			List<Empresa> empresasList = new ArrayList<Empresa>();
			List<TecnologiaDTO> tecnologiasList = new ArrayList<TecnologiaDTO>();
			List<EmpresaUsuario> empresaUsuarioListBd = new ArrayList<EmpresaUsuario>();
			EmpresaUsuarioItem empresaUsuarioItemBd = null;
			List<TecnologiaUsuario> tecnologiasUsuario = new ArrayList<TecnologiaUsuario>(); 
			List<EmpresaUsuarioItem> empresaUsuarioItensBd = new ArrayList<EmpresaUsuarioItem>();
			
			for (Object object : a) {
				EmpresaUsuarioDTO euRequest = modelMapper.map(object, EmpresaUsuarioDTO.class);
				//System.out.println(euRequest);
				
				String descricaoEmpresa = euRequest.getEmpresa().getEmpresa();
				Empresa e = registroService.findEmpresaByDescricao(descricaoEmpresa);
				
				if(e==null) {
					e = new Empresa();
					e.setDescricao(descricaoEmpresa);
					e.setAutonomo(euRequest.isAutonomo()? 'S': 'N');
					Empresa empresaBd = registroService.salvar(e);
					e = empresaBd;
					System.out.println("Empresa salva no bd: "+ e);
				}
				empresasList.add(e);
				//System.out.println(e);
				
				//int dificuldade = 0;
				//int totalTecnologias = 0;
				
				List<TecnologiaDTO>tecnologias = euRequest.getTecnologias();
				
				if(!tecnologias.isEmpty()) {
					tecnologiasList.addAll(tecnologias);
					//totalTecnologias = tecnologias.size();
					for (TecnologiaDTO tecDTO : tecnologias) {
						//dificuldade += tecDTO.getDificuldade();
						
						String descricaoTecnologia = tecDTO.getTecnologia();
						Tecnologia t = registroService.findTecnologiaByDescricao(descricaoTecnologia);
						if(t == null){
							t = new Tecnologia();
							t.setDescricao(tecDTO.getTecnologia());
							Tecnologia tecnologiaBd = registroService.salvar(t);
							System.out.println("Tecnologia no bd:" +tecnologiaBd);
						}
					}
				}
				/*if (dificuldade > 0) {
					dificuldade /= totalTecnologias;
				}*/
				
				EmpresaUsuario eu = new EmpresaUsuario();
				eu.setDataFim(euRequest.getDataFim());
				eu.setDataIni(euRequest.getDataIni());
				eu.setUsuario(usuarioBd);
				eu.setTrabalhoAtual(euRequest.isTrabalhoAtual());
				eu.setEmpresa(e);
				eu.setComplexidade(euRequest.getDificuldade());
				//eu.setComplexidade(dificuldade);
				
				eu.setDiversidade(euRequest.getDiversidade());
				eu.setDescricao(euRequest.getDescricao());
				//System.out.println(eu);
				EmpresaUsuario empresaUsuarioBd = registroService.salvar(eu);
				empresaUsuarioListBd.add(empresaUsuarioBd);
				System.out.println("Empresa Usuário salva no bd: "+ empresaUsuarioBd);
				
				if(!tecnologias.isEmpty()) {
					for (TecnologiaDTO tecDTO : tecnologias) {
						//EmpresaUsuarioItemDTO euiRequest = modelMapper.map(object, EmpresaUsuarioItemDTO.class);
						//System.out.println(euiRequest);
						EmpresaUsuarioItem eui = new EmpresaUsuarioItem();
						eui.setDataFim(tecDTO.getDataFim());
						eui.setDataIni(tecDTO.getDataIni());
						eui.setEmpUsu(empresaUsuarioBd);
						
						String descricaoTecnologia = tecDTO.getTecnologia();
						Tecnologia t = registroService.findTecnologiaByDescricao(descricaoTecnologia);
						
						eui.setTecnologia(t);
						eui.setFrequencia(tecDTO.getFrequenciaDeUso());
						eui.setUtilizaAtual(tecDTO.getUtilizaAtual()?'S':'N');
	
						empresaUsuarioItemBd = registroService.salvar(eui);
						empresaUsuarioItensBd.add(empresaUsuarioItemBd);
						System.out.println("Empresa Usuário Item salvo no bd: "+ empresaUsuarioItemBd);
					}
				}	
			}
			ArrayList b = (ArrayList) payload.get("pessoais");
			for (Object object : b) {
				TecnologiaDTO t = modelMapper.map(object, TecnologiaDTO.class);
				
				String descricaoTecnologia = t.getTecnologia();
				Tecnologia tec = registroService.findTecnologiaByDescricao(descricaoTecnologia);
				if(tec == null){
					tec = new Tecnologia();
					tec.setDescricao(t.getTecnologia());
					Tecnologia tecnologiaBd = registroService.salvar(tec);
					System.out.println("Tecnologia no bd:" +tecnologiaBd);
				}
				TecnologiaUsuarioDTO tu = modelMapper.map(object, TecnologiaUsuarioDTO.class);
	
				TecnologiaUsuario tecnologiaUsuario = new TecnologiaUsuario();
				tecnologiaUsuario.setConheceDesde(tu.getDataIni());
				tecnologiaUsuario.setTecnologia(tec);
				tecnologiaUsuario.setEstudaDesde(tu.getDataIni());
				tecnologiaUsuario.setEstudouAte(tu.getDataFim());
				tecnologiaUsuario.setUsuario(usuarioBd);
				//tecnologiaUsuario.setMaisde24Meses(tu.isMaisDe24Meses()?'S':'N');
				tecnologiaUsuario.setAplicacaoPratica(tu.getAplicacaoPratica());
				TecnologiaUsuario tecnologiaUsuarioBd = registroService.salvar(tecnologiaUsuario);
				tecnologiasUsuario.add(tecnologiaUsuarioBd);
				System.out.println(tecnologiasUsuario);
			}
			
			//criando resposta
			JsonNode userNode = mapper.convertValue(usuarioBd, JsonNode.class);		
			JsonNode empresaNode = mapper.convertValue(empresasList, JsonNode.class);
			JsonNode tecnologiasNode  = mapper.convertValue(tecnologiasList, JsonNode.class);
			JsonNode empresaUsuarioNode = mapper.convertValue(empresaUsuarioListBd, JsonNode.class);
			JsonNode empresaUsuarioItensNode = mapper.convertValue(empresaUsuarioItensBd, JsonNode.class);
			JsonNode tecnologiasUsuarioNode = mapper.convertValue(tecnologiasUsuario, JsonNode.class);
			
			ObjectNode registro = mapper.createObjectNode();
			
			registro.set("Usuario",userNode);
			registro.set("Empresa", empresaNode);
			registro.set("Tecnologias", tecnologiasNode);
			registro.set("Empresa Usuário", empresaUsuarioNode);
			registro.set("Empresa Usuário Item", empresaUsuarioItensNode);
			registro.set("Tecnologia Usuário", tecnologiasUsuarioNode);
			
			ObjectNode response = mapper.createObjectNode();
			response.set("Registro", registro);
			
			return ResponseEntity.created(null).body(response);
		}catch(Exception e) {
			JsonNode response = mapper.convertValue(e, JsonNode.class);
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/usuarios/{id}")
	ResponseEntity<?> getInfoRegistro(@PathVariable("id") Long userId) {
		List<TecnologiaUsuario> tecnologiasUsuario = registroService.listTecUsuByIdUsuario(userId);
		//List<EmpresaUsuario> empresasUsuario = registroService.listEmpUsuByIdUsuario(userId);
		List<EmpresaUsuarioItem> empresaUsuarioItens = registroService.listEmpUsuItemByIdUsuario(userId);
		
		InfoRegistro infoRegistro = new InfoRegistro();
		infoRegistro.setEmpresaUsuarioItens(empresaUsuarioItens);
		infoRegistro.setTecnologiasUsuario(tecnologiasUsuario);
		
		return ResponseEntity.ok().body(infoRegistro);
	}
}
