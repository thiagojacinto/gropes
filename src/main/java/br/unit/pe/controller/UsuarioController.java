package br.unit.pe.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unit.pe.exception.UserJaExistenteException;
import br.unit.pe.exception.UserNotFoundException;
import br.unit.pe.model.Usuario;
import br.unit.pe.service.UsuarioService;

@RestController
public class UsuarioController {
	@Autowired
	UsuarioService service;

	@GetMapping("/usuarios")
	Collection<Usuario> listar() {
		return service.listar();
	}

	@PostMapping("/usuarios")
	ResponseEntity<?> adicionar(@RequestBody Usuario usuario) {
		this.validateUser(usuario.getNome());

		Usuario result = service.salvar(usuario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	private void validateUser(String userName) {
		if (service.findByNome(userName).isPresent()) {
			throw new UserJaExistenteException(userName);
		}
	}

	@GetMapping("/usuarios/findByNome/{nome}")
	private Usuario procurar(@PathVariable("nome") String userName) {
		return service.findByNome(userName).orElseThrow(() -> new UserNotFoundException(userName));
	}

	@GetMapping("/usuarios/findById/{id}")
	private Usuario procurar(@PathVariable("id") Long id) {
		return service.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") long userId) {
		Usuario usuario = procurar(userId);
		service.excluir(usuario);
		return ResponseEntity.noContent().build();
	}
	
	public boolean existe(long id) {
		return service.existe(id);
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Usuario usuario, @PathVariable("id") long userId) {
		if(existe(userId)) {
			usuario.setId(userId);
			service.salvar(usuario);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
