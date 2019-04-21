package br.com.api.springbootrestapi.dto;

import br.com.api.springbootrestapi.entities.Pessoa;

/**
 * Classe DTO(Data transfer object) que representa a entidade {@link Pessoa}.
 */
public class PessoaDTO implements DTO {

	private final Integer id;
	private final String nome;
	private final Integer idade;

	/**
	 * Construtor da classe.
	 *
	 * @param id
	 * @param nome
	 * @param idade
	 */
	public PessoaDTO(Integer id, String nome, Integer idade) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}

	/**
	 * Construtor da classe.
	 *
	 * @param nome
	 * @param idade
	 */
	public PessoaDTO(String nome, Integer idade) {
		this.id = null;
		this.nome = nome;
		this.idade = idade;
	}

	/**
	 * Construtor da classe.
	 */
	public PessoaDTO() {
		this.id = null;
		this.nome = null;
		this.idade = null;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getIdade() {
		return idade;
	}
}
