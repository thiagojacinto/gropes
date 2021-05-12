package br.unit.pe.exception;

public class EmpresaJaExistenteException extends RuntimeException {

	public EmpresaJaExistenteException(String descricao) {
		super("A empresa " + descricao +  " já existe");
	}
}
