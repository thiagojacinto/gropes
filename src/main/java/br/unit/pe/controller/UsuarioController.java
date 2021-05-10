package br.unit.pe.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.unit.pe.exception.UserJaExistenteException;
import br.unit.pe.exception.UserNotFoundException;
import br.unit.pe.model.EmpresaDTO;
import br.unit.pe.model.EmpresaUsuarioDTO;
import br.unit.pe.model.EmpresaUsuarioItemDTO;
import br.unit.pe.model.Tecnologia;
import br.unit.pe.model.TecnologiaDTO;
import br.unit.pe.model.TecnologiaUsuarioDTO;
import br.unit.pe.model.Usuario;
import br.unit.pe.model.UsuarioDTO;
import br.unit.pe.service.UsuarioService;

@RestController
public class UsuarioController {
	@Autowired
	UsuarioService service;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/usuarios")
	Collection<Usuario> listar() {
		return service.listar();
	}

	/*
	 * @PostMapping("/usuarios") ResponseEntity<?> adicionar(@RequestBody Usuario
	 * usuario) { this.validateUser(usuario.getNome());
	 * 
	 * Usuario result = service.salvar(usuario); URI location =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (result.getId()) .toUri();
	 * 
	 * return ResponseEntity.created(location).build(); }
	 */

	private void validateUser(String userName) {
		if (service.findByNome(userName).isPresent()) {
			throw new UserJaExistenteException(userName);
		}
	}

	@GetMapping("/usuarios/findByNome/{nome}")
	Usuario procurar(@PathVariable("nome") String userName) {
		return service.findByNome(userName).orElseThrow(() -> new UserNotFoundException(userName));
	}

	@GetMapping("/usuarios/findById/{id}")
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

	@PostMapping("/usuarios")
	ResponseEntity<?> registrar(@RequestBody Map<String, Object> payload) {
		UsuarioDTO u = modelMapper.map(payload.get("basico"), UsuarioDTO.class);
		System.out.println(u);
		ArrayList a = (ArrayList) payload.get("profissionais");

		for (Object object : a) {
			EmpresaUsuarioDTO eu = modelMapper.map(object, EmpresaUsuarioDTO.class);
			EmpresaUsuarioItemDTO eui = modelMapper.map(object, EmpresaUsuarioItemDTO.class);
			eui.setEmpresaUsuario(eu);

			System.out.println(eui);
		}
		ArrayList b = (ArrayList) payload.get("pessoais");
		for (Object object : b) {
			TecnologiaDTO t = modelMapper.map(object, TecnologiaDTO.class);
			TecnologiaUsuarioDTO tu = modelMapper.map(object, TecnologiaUsuarioDTO.class);
			tu.setTecnologia(t);
			System.out.println(tu);
		}

		return ResponseEntity.ok().build();
	}
}
