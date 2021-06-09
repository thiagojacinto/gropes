package br.unit.pe.exception;

import java.util.Date;

public class DataInvalidaException extends RuntimeException {

	public DataInvalidaException(Date date, String msg) {
		super(msg + date);
	}
	public DataInvalidaException(Date dateFim, Date dateIni, String msg) {
		super(msg + dateFim  + "Data inicial: " + dateIni);
	}
	public DataInvalidaException() {
		super("Data inválida");
	}
}
