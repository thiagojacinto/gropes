package br.unit.pe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.exception.ListaVaziaException;
import br.unit.pe.model.Usuario;
import br.unit.pe.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> listar() {
		
		return usuarioRepository.findAllByOrderByIdAsc().orElseThrow(
				() -> new ListaVaziaException());
	}

	public Optional<Usuario> findByNome(String nome) {
		return usuarioRepository.findByNome(nome);
	}
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	public Optional<Usuario> findById(long id) {
		return usuarioRepository.findById(id);
	}
	public void excluir(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	public boolean existe(long id) {
		return usuarioRepository.existsById(id);
	}

	public boolean login(String email,String senha) {
		if (usuarioRepository.login(email, senha).isPresent()) {
			return true;
		}
		return false;
	}
}
