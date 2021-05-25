package br.unit.pe.exception;

public class EmailJaExistenteException extends RuntimeException {

	public EmailJaExistenteException() {
		super("E-mail ja cadastrado");
	}
}
