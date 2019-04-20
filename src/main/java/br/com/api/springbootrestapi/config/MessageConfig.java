package br.com.api.springbootrestapi.config;

import java.util.ResourceBundle;

/**
 * Classe de Configurações de mensagem.
 */
public class MessageConfig {

	private static final ResourceBundle RESOURCE_MSG = ResourceBundle.getBundle("crud/messages");

	/**
	 * Construtor privado da classe para impedir de ser instanciado.
	 */
	private MessageConfig() {
		throw new IllegalStateException("Classe de configuração não pode ser instanciada!");
	}

	/**
	 * Retorna a mensagem conforme a chave fornecida.
	 *
	 * @param code
	 * @return
	 */
	public static String getMensagem(final MessageCode code) {
		return getMensagem(code.toString());
	}

	/**
	 * Retorna a mensagem conforme a chave fornecida.
	 *
	 * @param chave
	 * @return
	 */
	public static String getMensagem(final String chave) {
		return RESOURCE_MSG.getString(chave);
	}
}
