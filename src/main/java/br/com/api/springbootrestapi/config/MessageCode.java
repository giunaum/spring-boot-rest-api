package br.com.api.springbootrestapi.config;

/**
 * Enum com os código de exceções/mensagens.
 */
public enum MessageCode {

	SUCESSO_SALVAR_PESSOA("MSG-SUCESSO-SALVAR-PESSOA"),
	SUCESSO_ALTERAR_PESSOA("MSG-SUCESSO-ALTERAR-PESSOA"),
	SUCESSO_EXCLUIR_PESSOA("MSG-SUCESSO-EXCLUIR-PESSOA"),
	FALHA_SALVAR_PESSOA("MSG-FALHA-SALVAR-PESSOA"),
	FALHA_ALTERAR_PESSOA("MSG-FALHA-ALTERAR-PESSOA"),
	FALHA_EXCLUIR_PESSOA("MSG-FALHA-EXCLUIR-PESSOA"),
	PESSOA_NAO_SALVO("MSG-PESSOA-NAO-SALVO"),
	PESSOA_NAO_ATUALIZADO("MSG-PESSOA-NAO-ATUALIZADO"),
	FALHA_RECUPERAR_PESSOAS("MSG-FALHA-RECUPERAR-PESSOAS"),
	PESSOAS_NAO_ENCONTRADOS("MSG-PESSOAS-NAO-ENCONTRADOS"),
	FALHA_RECUPERAR_PESSOA("MSG-FALHA-RECUPERAR-PESSOA"),
	PESSOA_NAO_ENCONTRADO("MSG-PESSOA-NAO-ENCONTRADO"),
	PARAMETROS_OBRIGATORIOS("MSG-PARAMETROS-OBRIGATORIOS"),
	PESSOA_NAO_FORNECIDO("MSG-PESSOA-NAO-FORNECIDO");

	private final String chave;

	/**
	 * Construtor do Enum.
	 *
	 * @param chave
	 */
	MessageCode(String chave) {
		this.chave = chave;
	}

	@Override
	public String toString() {
		return chave;
	}
}
