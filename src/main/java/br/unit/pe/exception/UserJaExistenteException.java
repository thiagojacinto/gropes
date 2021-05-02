package br.unit.pe.exception;

public class UserJaExistenteException extends RuntimeException {

	public UserJaExistenteException(String userName) {
		super("O usuário " + userName +  " já existe");
	}
}
