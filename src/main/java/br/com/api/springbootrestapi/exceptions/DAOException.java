package br.com.api.springbootrestapi.exceptions;

import br.com.api.springbootrestapi.persistence.DAO;

/**
 * Classe de exceção da classe {@link DAO}.
 */
public class DAOException extends Exception {

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}
}
