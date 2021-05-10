package br.unit.pe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.unit.pe.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<List <Usuario>> findAllByOrderByIdAsc();

	Optional<Usuario> findByNome(String nome);

	@Query( value = "SELECT email from Usuario where Usuario.email = :email and Usuario.senha = :senha",nativeQuery = true)
	Optional<String> login(@Param("email") String email,@Param("senha") String senha);
}
