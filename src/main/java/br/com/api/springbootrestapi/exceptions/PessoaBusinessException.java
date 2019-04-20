package br.com.api.springbootrestapi.exceptions;

import br.com.api.springbootrestapi.business.PessoaBusiness;
import br.com.api.springbootrestapi.config.MessageCode;
import br.com.api.springbootrestapi.config.MessageConfig;
import br.com.api.springbootrestapi.util.Util;

import java.io.Serializable;
import java.util.List;

/**
 * Classe de exceção da classe {@link PessoaBusiness}.
 */
public class PessoaBusinessException extends Exception implements Serializable {

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param cause
	 */
	public PessoaBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 */
	public PessoaBusinessException(String message) {
		super(message);
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param code
	 */
	public PessoaBusinessException(MessageCode code) {
		super(MessageConfig.getMensagem(code));
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param parametros
	 */
	public PessoaBusinessException(String message, List<String> parametros) {
		super(Util.formatarString(Boolean.TRUE, message, (String[]) parametros.toArray()));
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param parametros
	 */
	public PessoaBusinessException(String message, String... parametros) {
		super(Util.formatarString(Boolean.TRUE, message, parametros));
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param code
	 * @param parametros
	 */
	public PessoaBusinessException(MessageCode code, List<String> parametros) {
		super(Util.formatarString(Boolean.TRUE, MessageConfig.getMensagem(code), (String[]) parametros.toArray()));
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param code
	 * @param parametros
	 */
	public PessoaBusinessException(MessageCode code, String... parametros) {
		super(Util.formatarString(Boolean.TRUE, MessageConfig.getMensagem(code), parametros));
	}
}
