package com.renatovirto.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> lista = new ArrayList<>();

	public ValidationError(Integer status, String mensagem, Long timeStamp) {
		super(status, mensagem, timeStamp);
	}

	public List<FieldMessage> getErros() {
		return lista;
	}

	public void setLista(List<FieldMessage> lista) {
		this.lista = lista;
	}
	
	public void addError(String fieldName, String mensagem) {
		lista.add(new FieldMessage(fieldName, mensagem));
	}
}
