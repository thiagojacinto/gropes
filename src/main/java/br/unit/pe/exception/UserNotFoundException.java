package br.unit.pe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String userName) {
		super("Não foi possível encontrar o usuário '" + userName + "'.");
	}
	public UserNotFoundException(Long id) {
		super("Não foi possível encontrar o usuário de id '" + id + "'.");
	}
}
